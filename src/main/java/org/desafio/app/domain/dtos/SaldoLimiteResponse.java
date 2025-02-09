package org.desafio.app.domain.dtos;

public class SaldoLimiteResponse {

    private int saldo;
    private int limite;

    public SaldoLimiteResponse(int saldo, int limite) {
        this.saldo = saldo;
        this.limite = limite;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getLimite() {
        return limite;
    }
}
