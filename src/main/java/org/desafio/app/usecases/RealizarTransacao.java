package org.desafio.app.usecases;

import org.desafio.app.domain.dtos.SaldoLimiteResponse;
import org.desafio.app.domain.entities.Cliente;
import org.desafio.app.domain.entities.Transacao;
import org.desafio.app.interfaces.repository.ClienteRepository;
import org.desafio.app.utils.exceptions.ClientNotFoundException;
import org.desafio.app.utils.exceptions.DescriptionTooLongException;
import org.desafio.app.utils.exceptions.InsufficientBalanceException;
import org.desafio.app.utils.exceptions.InvalidTransactionType;

public class RealizarTransacao {

    private static final int MAX_DESCRICAO_LENGTH = 10;
    private static final String CREDITO = "c";
    private static final String DEBITO = "d";

    private final ClienteRepository repository;

    public RealizarTransacao(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente executar(int clienteId, Transacao transacao) {
        Cliente cliente = obterCliente(clienteId);
        validarTransacao(transacao);
        int novoSaldo = calcularNovoSaldo(cliente, transacao);
        validarSaldo(novoSaldo, cliente.getLimite());
        atualizarCliente(clienteId, cliente, novoSaldo, transacao);
        return cliente;
    }

    private Cliente obterCliente(int clienteId) {
        Cliente cliente = repository.buscarPorId(clienteId);
        if (cliente == null) {
            throw new ClientNotFoundException();
        }
        return cliente;
    }

    private void validarTransacao(Transacao transacao) {
        if (transacao.getDescricao().length() > MAX_DESCRICAO_LENGTH) {
            throw new DescriptionTooLongException();
        }
    }

    private int calcularNovoSaldo(Cliente cliente, Transacao transacao) {
        int novoSaldo = cliente.getSaldo();
        switch (transacao.getTipo()) {
            case CREDITO:
                novoSaldo += transacao.getValor();
                break;
            case DEBITO:
                novoSaldo -= transacao.getValor();
                break;
            default:
                throw new InvalidTransactionType();
        }
        return novoSaldo;
    }

    private void validarSaldo(int novoSaldo, int limite) {
        if (novoSaldo < -limite) {
            throw new InsufficientBalanceException();
        }
    }

    private void atualizarCliente(int clienteId, Cliente cliente, int novoSaldo, Transacao transacao) {
        cliente.setSaldo(novoSaldo);
        repository.atualizarSaldo(clienteId, novoSaldo);
        repository.salvarTransacao(clienteId, transacao);
    }

    public SaldoLimiteResponse obterSaldoLimite(int id) {
        Cliente cliente = repository.buscarPorId(id);
        return new SaldoLimiteResponse(cliente.getSaldo(), cliente.getLimite());
    }
}