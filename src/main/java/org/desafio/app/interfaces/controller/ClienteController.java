package org.desafio.app.interfaces.controller;

import org.desafio.app.domain.dtos.SaldoLimiteResponse;
import org.desafio.app.domain.entities.Transacao;
import org.desafio.app.usecases.RealizarTransacao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/clientes/{id}/transacoes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteController {

    private final RealizarTransacao realizarTransacao;

    public ClienteController(RealizarTransacao realizarTransacao) {
        this.realizarTransacao = realizarTransacao;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response criarTransacao(
            @PathParam("id") int id,
            Transacao transacao) {
        realizarTransacao.executar(id, transacao);
        SaldoLimiteResponse saldoLimiteResponse = realizarTransacao.obterSaldoLimite(id);
        return Response.ok(saldoLimiteResponse).build();
    }


}
