package usecases;


import org.desafio.app.domain.dtos.SaldoLimiteResponse;
import org.desafio.app.domain.entities.Cliente;
import org.desafio.app.domain.entities.Transacao;
import org.desafio.app.interfaces.repository.ClienteRepository;
import org.desafio.app.usecases.RealizarTransacao;
import org.desafio.app.utils.exceptions.ClientNotFound;
import org.desafio.app.utils.exceptions.InvalidBalance;
import org.desafio.app.utils.exceptions.InvalidDescription;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RealizarTransacaoTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private RealizarTransacao realizarTransacao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void executar_clienteNaoEncontrado() {
        when(repository.buscarPorId(anyInt())).thenReturn(null);

        assertThrows(ClientNotFound.class, () -> realizarTransacao.executar(1, new Transacao()));
    }

    @Test
    void executar_transacaoValida() {
        Cliente cliente = new Cliente();
        cliente.setSaldo(100);
        cliente.setLimite(50);

        Transacao transacao = new Transacao();
        transacao.setDescricao("Compra");
        transacao.setTipo("d");
        transacao.setValor(30);

        when(repository.buscarPorId(anyInt())).thenReturn(cliente);

        Cliente result = realizarTransacao.executar(1, transacao);

        assertEquals(70, result.getSaldo());
        verify(repository).atualizarSaldo(1, 70);
        verify(repository).salvarTransacao(1, transacao);
    }

    @Test
    void validarTransacao_descricaoInvalida() {
        Cliente cliente = new Cliente();
        Transacao transacao = new Transacao();
        transacao.setDescricao("Descricao muito longa");

        assertThrows(InvalidDescription.class, () -> realizarTransacao.executar(1, transacao));
    }

    @Test
    void validarTransacao_tipoInvalido() {
        Cliente cliente = new Cliente();
        Transacao transacao = new Transacao();
        transacao.setDescricao("Compra");
        transacao.setTipo("x");

        assertThrows(ClientNotFound.class, () -> realizarTransacao.executar(1, transacao));
    }

    @Test
    void validarTransacao_saldoInsuficiente() {
        Cliente cliente = new Cliente();
        cliente.setSaldo(100);
        cliente.setLimite(50);

        Transacao transacao = new Transacao();
        transacao.setDescricao("Compra");
        transacao.setTipo("d");
        transacao.setValor(200);

        when(repository.buscarPorId(anyInt())).thenReturn(cliente);

        assertThrows(InvalidBalance.class, () -> realizarTransacao.executar(1, transacao));
    }

    @Test
    void obterSaldoLimite_clienteExistente() {
        Cliente cliente = new Cliente();
        cliente.setSaldo(100);
        cliente.setLimite(50);

        when(repository.buscarPorId(anyInt())).thenReturn(cliente);

        SaldoLimiteResponse response = realizarTransacao.obterSaldoLimite(1);

        assertEquals(100, response.getSaldo());
        assertEquals(50, response.getLimite());
    }
}
