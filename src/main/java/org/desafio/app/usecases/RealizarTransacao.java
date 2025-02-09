package org.desafio.app.usecases;

import org.desafio.app.domain.dtos.SaldoLimiteResponse;
import org.desafio.app.domain.entities.Cliente;
import org.desafio.app.domain.entities.Transacao;
import org.desafio.app.interfaces.repository.ClienteRepository;
import org.desafio.app.utils.exceptions.ClientNotFound;
import org.desafio.app.utils.exceptions.InvalidBalance;

public class RealizarTransacao {

    private final ClienteRepository repository;

    public RealizarTransacao(ClienteRepository repository) {
        this.repository = repository;
    }

   public Cliente executar(int clienteId, Transacao transacao) {
       Cliente cliente = repository.buscarPorId(clienteId);
       if (cliente == null) {
           throw new ClientNotFound("Cliente não encontrado na base de dados");
       }

       int novoSaldo = calcularNovoSaldo(cliente, transacao);
       validarSaldo(novoSaldo, cliente.getLimite());

       cliente.setSaldo(novoSaldo);
       repository.atualizarSaldo(clienteId, novoSaldo);
       repository.salvarTransacao(clienteId, transacao);
       return cliente;
   }

   private int calcularNovoSaldo(Cliente cliente, Transacao transacao) {
       int novoSaldo = cliente.getSaldo();
       switch (transacao.getTipo()) {
           case "c":
               novoSaldo += transacao.getValor();
               break;
           case "d":
               novoSaldo -= transacao.getValor();
               break;
           default:
               throw new InvalidBalance("Tipo de transação inválido");
       }
       return novoSaldo;
   }

   private void validarSaldo(int novoSaldo, int limite) {
       if (novoSaldo < -limite) {
           throw new IllegalArgumentException("Saldo insuficiente para débito");
       }
   }

    public SaldoLimiteResponse obterSaldoLimite(int id) {
        Cliente cliente = repository.buscarPorId(id);
        return new SaldoLimiteResponse(cliente.getSaldo(), cliente.getLimite());
    }
}
