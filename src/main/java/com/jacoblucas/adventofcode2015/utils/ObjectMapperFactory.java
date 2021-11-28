package com.jacoblucas.adventofcode2015.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperFactory {
    private static ObjectMapper instance = null;

    public static ObjectMapper get() {
        if (instance == null) {
            final ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new Jdk8Module());
            objectMapper.registerModule(new GuavaModule());
            objectMapper.registerModule(new JavaTimeModule());
            instance = objectMapper;
        }

        return instance;
    }
}
