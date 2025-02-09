package org.desafio.app.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.desafio.app.infrastructure.db.DatabaseConfig;


public class AppConfiguration extends Configuration {

    @JsonProperty("database")
    private DatabaseConfig database;

    public DatabaseConfig getDatabase() {
        return database;
    }
}
