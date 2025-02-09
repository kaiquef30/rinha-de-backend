package org.desafio.app.interfaces.controller;

import org.desafio.app.usecases.ObterExtrato;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/clientes/{id}/extrato")
@Produces(MediaType.APPLICATION_JSON)
public class ExtratoController {

    private final ObterExtrato obterExtrato;

    public ExtratoController(ObterExtrato obterExtrato) {
        this.obterExtrato = obterExtrato;
    }

    @GET
    public Response obterExtrato(@PathParam("id") int id) {
            return Response.ok(obterExtrato.executar(id)).build();
    }

}
