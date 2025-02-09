package org.desafio.app;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import org.desafio.app.configuration.AppConfiguration;
import org.desafio.app.infrastructure.db.DatabaseConfig;
import org.desafio.app.infrastructure.db.DatabaseConnection;
import org.desafio.app.interfaces.controller.ClienteController;
import org.desafio.app.interfaces.controller.ExtratoController;
import org.desafio.app.interfaces.repository.impl.ClienteRepositoryImpl;
import org.desafio.app.usecases.ObterExtrato;
import org.desafio.app.usecases.RealizarTransacao;

public class ApplicationMain extends Application<AppConfiguration> {

    public static void main(String[] args) throws Exception {
        new ApplicationMain().run(args);
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) {
        DatabaseConfig dbConfig = configuration.getDatabase();

        DatabaseConnection databaseConnection = new DatabaseConnection(
                dbConfig.getUrl(),
                dbConfig.getUser(),
                dbConfig.getPassword()
        );


        ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl(databaseConnection.getDataSource());
        RealizarTransacao realizarTransacao = new RealizarTransacao(clienteRepository);
        ObterExtrato obterExtrato = new ObterExtrato(clienteRepository);


        environment.jersey().register(new ClienteController(realizarTransacao));
        environment.jersey().register(new ExtratoController(obterExtrato));
    }
}
