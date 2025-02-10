package org.desafio.app.domain.entities;

public class Cliente {

    private int id;
    private int limite;
    private int saldo;

    public Cliente(int id, int limite, int saldo) {
        this.id = id;
        this.limite = limite;
        this.saldo = saldo;
    }

    public Cliente() {
    }

    public int getId() {
        return id;
    }

    public int getLimite() {
        return limite;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setLimite(int limite) {
        this.limite = limite;
    }

}
