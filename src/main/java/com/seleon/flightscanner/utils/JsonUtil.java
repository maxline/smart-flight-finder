package com.seleon.flightscanner.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;
import java.util.stream.Collectors;

public class JsonUtil {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.registerModule(new JavaTimeModule());
        MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {

        T result;
        try {
            result = MAPPER.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
        return result;
    }

    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        List<T> fares;
        try {
            JavaType type = MAPPER.getTypeFactory().constructCollectionType(List.class, clazz);
            fares = MAPPER.readValue(jsonString, type);
//          fares = MAPPER.readValue(jsonString, new TypeReference<>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return fares;
    }

    public static <T> String listToNewLineString(List<T> list) {
        return list.stream()
                .map(Object::toString)
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
