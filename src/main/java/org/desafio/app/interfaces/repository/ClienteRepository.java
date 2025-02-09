package org.desafio.app.interfaces.repository;

import org.desafio.app.domain.entities.Cliente;
import org.desafio.app.domain.entities.Transacao;

import java.util.List;

public interface ClienteRepository {

    Cliente buscarPorId(int id);
    void atualizarSaldo(int id, int novoSaldo);
    void salvarTransacao(int clienteId, Transacao transacao);
    List<Transacao> buscarUltimasTransacoes(int clienteId);

}
