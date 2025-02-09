package org.desafio.app.infrastructure.db;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DatabaseConfig {

    @JsonProperty
    private String url;

    @JsonProperty
    private String user;

    @JsonProperty
    private String password;

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

}
