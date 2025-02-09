package org.desafio.app.infrastructure.db;

public class SQLStatements {
    public static final String BUSCAR_CLIENTE = "SELECT id, limite, saldo FROM clientes WHERE id = ?";
    public static final String ATUALIZAR_SALDO = "UPDATE clientes SET saldo = ? WHERE id = ?";
    public static final String SALVAR_TRANSACAO = "INSERT INTO transacoes (cliente_id, valor, tipo, descricao, realizada_em) VALUES (?, ?, ?, ?, ?)";
    public static final String BUSCAR_TRANSACOES = "SELECT valor, tipo, descricao, realizada_em FROM transacoes WHERE cliente_id = ? ORDER BY realizada_em DESC LIMIT 10";
}
