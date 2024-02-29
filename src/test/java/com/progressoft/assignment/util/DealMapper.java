package com.progressoft.assignment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.assignment.model.Deal;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class DealMapper {
    public static String objectToStringJson(ObjectMapper mapper, Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Deal> jsonToListObject(ObjectMapper mapper, String json) {
        List<Deal> list;
        try {
            list = mapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.debug(String.format("Mapper failed to convert following json: %s to object", json));
            throw new RuntimeException(e);
        }
        return list;
    }

    public static Deal jsonToSingleObject(ObjectMapper mapper, String json) {
        Deal deal;
        try {
            deal = mapper.readValue(json, Deal.class);
        } catch (JsonProcessingException e) {
            log.debug(String.format("Mapper failed to convert following json: %s to object", json));
            throw new RuntimeException(e);
        }
        return deal;
    }
}
