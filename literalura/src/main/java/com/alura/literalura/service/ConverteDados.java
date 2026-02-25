package com.alura.literalura.service;

import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.type.CollectionType;

import java.util.List;

public class ConverteDados implements IConverteDados {
    ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T obterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
