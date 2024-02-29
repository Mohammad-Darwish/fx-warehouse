package com.progressoft.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.assignment.dto.DealDto;
import com.progressoft.assignment.model.Deal;
import com.progressoft.assignment.pojo.SaveDealsResponse;
import com.progressoft.assignment.service.DealService;
import com.progressoft.assignment.util.DealMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.stream.Stream;

import static com.progressoft.assignment.util.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DealController.class)
class DealControllerTest {

    @MockBean
    private DealService dealService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @ParameterizedTest
    @MethodSource("dealTestData")
    void addDealsTest(List<Deal> inputDeals, SaveDealsResponse saveDealsResponse, int expectedStatus) throws Exception {
        when(dealService.saveDeals(any())).thenReturn(saveDealsResponse);

        ResultActions perform = mockMvc.perform(
            post("/fx-warehouse/v1/deals")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(inputDeals))
        );
        perform.andExpect(status().is(expectedStatus));
    }

    private static Stream<Arguments> dealTestData() {
        return Stream.of(
            Arguments.of(JOD_EUR_USD_DEALS, SAVE_DEALS_RESPONSE, 200),
            Arguments.of(JOD_EUR_DEALS, SAVE_DEALS_RESPONSE_NEW, 201),
            Arguments.of(JOD_EUR_DEALS, SAVE_DEALS_RESPONSE_ALREADY_EXIST, 409)
        );
    }

    @Test
    void getAllDealsTest() throws Exception {
        when(dealService.readAllDeals(any(), any(), any()))
            .thenReturn(JOD_EUR_USD_DEALS_DTO);
        MockHttpServletResponse response = mockMvc.perform(
                get("/fx-warehouse/v1/deals")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(status().isOk())
            .andReturn().getResponse();
        List<DealDto> deals = DealMapper.jsonToListObject(mapper, response.getContentAsString());
        assertEquals(JOD_EUR_USD_DEALS_DTO, deals);
    }

    @Test
    void getDealByIdTest() throws Exception {
        when(dealService.readDeal(JOD_DEAL.getId().toString()))
            .thenReturn(JOD_DEAL_DTO);
        MockHttpServletResponse response = mockMvc.perform(
                get(String.format("/fx-warehouse/v1/deals/%s", JOD_DEAL.getId()))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(status().isOk())
            .andReturn().getResponse();
        DealDto deals = DealMapper.jsonToSingleObject(mapper, response.getContentAsString());
        assertEquals(JOD_DEAL_DTO, deals);
    }
}