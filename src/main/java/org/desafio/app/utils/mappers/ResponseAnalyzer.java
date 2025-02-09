package org.desafio.app.utils.mappers;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import org.desafio.app.utils.exceptions.ClientNotFound;
import org.desafio.app.utils.exceptions.InvalidBalance;
import org.desafio.app.utils.exceptions.InvalidDescription;
import org.desafio.app.utils.exceptions.InvalidTransaction;

public class ResponseAnalyzer {

    private static final Map<Class<? extends Exception>, Response.Status> exceptionStatusMap = new HashMap<>();

    static {
        exceptionStatusMap.put(ClientNotFound.class, Response.Status.NOT_FOUND);
        exceptionStatusMap.put(InvalidBalance.class, Response.Status.BAD_REQUEST);
        exceptionStatusMap.put(InvalidTransaction.class, Response.Status.FORBIDDEN);
        exceptionStatusMap.put(InvalidDescription.class, Response.Status.BAD_REQUEST);
    }

    public static Response analyze(Exception exception) {
        Response.Status status = exceptionStatusMap.getOrDefault(exception.getClass(), Response.Status.INTERNAL_SERVER_ERROR);
        String message = exceptionStatusMap.containsKey(exception.getClass()) ? exception.getMessage() : "Unknown error";
        return Response.status(status).entity(message).build();
    }
}