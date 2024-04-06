package com.sanrenxing.service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonConverter {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> String serialize(List<T> objects) {
        try {
            return objectMapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "[]"; // Default to empty JSON array in case of error
        }
    }

    public static <T> String serialize(T objects) {
        try {
            return objectMapper.writeValueAsString(objects);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "null"; // Default to null value in case of error
        }
    }

    public static <T> List<T> deserialize(String json, Class<T> valueType) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<T>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return List.of(); // Default to empty list in case of error
        }
    }
}
