package com.seleon.flightscanner.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.seleon.flightscanner.ryanair.dto.Fare;

import java.util.List;
import java.util.stream.Collectors;

public class JsonUtil {
    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // else LocalDateTime will serialize as timestamp

        T result;
        try {
            result = mapper.readValue(jsonString, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON", e);
        }
        return result;
    }

    public static List<Fare> jsonToList(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        List<Fare> fares;
        try {
            fares = mapper.readValue(jsonString, new TypeReference<>() {
            });
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
