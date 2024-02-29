package com.progressoft.assignment.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.assignment.dto.DealDto;
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

    public static List<DealDto> jsonToListObject(ObjectMapper mapper, String json) {
        List<DealDto> list;
        try {
            list = mapper.readValue(json, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.debug(String.format("Mapper failed to convert following json: %s to object", json));
            throw new RuntimeException(e);
        }
        return list;
    }

    public static DealDto jsonToSingleObject(ObjectMapper mapper, String json) {
        DealDto dealDto;
        try {
            dealDto = mapper.readValue(json, DealDto.class);
        } catch (JsonProcessingException e) {
            log.debug(String.format("Mapper failed to convert following json: %s to object", json));
            throw new RuntimeException(e);
        }
        return dealDto;
    }
}
