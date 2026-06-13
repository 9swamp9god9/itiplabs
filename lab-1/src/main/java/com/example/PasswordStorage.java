package com.example;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

public class PasswordStorage {

    private ObjectMapper mapper = new ObjectMapper();

    public void save(String password) throws Exception {
        mapper.writeValue(new File("password.json"), password);
    }

    public String load() throws Exception {
        return mapper.readValue(new File("password.json"), String.class);
    }
}