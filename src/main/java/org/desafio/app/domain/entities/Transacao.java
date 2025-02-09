package org.desafio.app.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

public class Transacao {

    @NotNull
    private int valor;

    @NotNull
    private String tipo;

    @Size(max = 10)
    private String descricao;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private OffsetDateTime realizadaEm;

    public Transacao(int valor, String tipo, String descricao, OffsetDateTime realizadaEm) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.realizadaEm = realizadaEm;
    }

    public Transacao() {
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getRealizadaEm() {
        return realizadaEm != null ? realizadaEm.toLocalDateTime() : null;
    }

    public void setRealizadaEm(OffsetDateTime realizadaEm) {
        this.realizadaEm = realizadaEm;
    }
}