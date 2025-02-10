package org.desafio.app.usecases;

import org.desafio.app.domain.entities.Cliente;
import org.desafio.app.domain.entities.Transacao;
import org.desafio.app.interfaces.repository.ClienteRepository;
import org.desafio.app.utils.exceptions.ClientNotFoundException;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObterExtrato {

    private final ClienteRepository repository;

    public ObterExtrato(ClienteRepository repository) {
        this.repository = repository;
    }

   public Map<String, Object> executar(int clienteId) {
        Cliente cliente = repository.buscarPorId(clienteId);
        if (cliente == null) {
            throw new ClientNotFoundException();
        }

        List<Transacao> transacoes = repository.buscarUltimasTransacoes(clienteId);

        Map<String, Object> extrato = criarExtrato(cliente, transacoes);

        return extrato;
    }

    private Map<String, Object> criarExtrato(Cliente cliente, List<Transacao> transacoes) {
        Map<String, Object> extrato = new HashMap<>();
        Map<String, Object> saldo = new HashMap<>();

        saldo.put("total", cliente.getSaldo());
        saldo.put("data_extrato", OffsetDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        saldo.put("limite", cliente.getLimite());
        extrato.put("saldo", saldo);
        extrato.put("ultimas_transacoes", transacoes);

        return extrato;
    }

}
