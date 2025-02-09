package org.desafio.app.interfaces.repository.impl;

import org.desafio.app.domain.entities.Cliente;
import org.desafio.app.domain.entities.Transacao;
import org.desafio.app.infrastructure.db.SQLStatements;
import org.desafio.app.interfaces.repository.ClienteRepository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteRepositoryImpl implements ClienteRepository {

    private static final Logger LOGGER = Logger.getLogger(ClienteRepositoryImpl.class.getName());
    private final DataSource dataSource;

    public ClienteRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Cliente buscarPorId(int id) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.BUSCAR_CLIENTE)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Cliente(
                        resultSet.getInt("id"),
                        resultSet.getInt("limite"),
                        resultSet.getInt("saldo")
                );
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching client by ID", e);
        }
        return null;
    }

    @Override
    public void atualizarSaldo(int id, int novoSaldo) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.ATUALIZAR_SALDO)) {
            statement.setInt(1, novoSaldo);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error updating balance", e);
        }
    }

    @Override
    public void salvarTransacao(int clienteId, Transacao transacao) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.SALVAR_TRANSACAO)) {
            statement.setInt(1, clienteId);
            statement.setInt(2, transacao.getValor());
            statement.setString(3, transacao.getTipo());
            statement.setString(4, transacao.getDescricao());
            statement.setTimestamp(5, getTimestamp(transacao.getRealizadaEm()));
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error saving transaction", e);
        }
    }

    public List<Transacao> buscarUltimasTransacoes(int clienteId) {
        List<Transacao> transacoes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(SQLStatements.BUSCAR_TRANSACOES)) {
            statement.setInt(1, clienteId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                transacoes.add(new Transacao(
                        resultSet.getInt("valor"),
                        resultSet.getString("tipo"),
                        resultSet.getString("descricao"),
                        resultSet.getTimestamp("realizada_em").toInstant().atOffset(ZoneOffset.UTC)
                ));
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error fetching last transactions", e);
        }
        return transacoes;
    }

    private Timestamp getTimestamp(LocalDateTime dateTime) {
        if (dateTime != null) {
            return Timestamp.valueOf(dateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        } else {
            return Timestamp.valueOf(LocalDateTime.now());
        }
    }
}