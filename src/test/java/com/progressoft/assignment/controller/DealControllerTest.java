package com.progressoft.assignment.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.progressoft.assignment.model.Deal;
import com.progressoft.assignment.service.DealService;
import com.progressoft.assignment.util.DealMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static com.progressoft.assignment.util.Constants.JOD_DEAL;
import static com.progressoft.assignment.util.Constants.THREE_DEALS;
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

    @Test
    void addDealsTest() throws Exception {
        mockMvc.perform(
                post("/fx-warehouse/v1/deals").
                    contentType(MediaType.APPLICATION_JSON_VALUE).
                    content(mapper.writeValueAsString(List.of(JOD_DEAL)))
            )
            .andExpect(status().isOk());
    }

    @Test
    void getAllDealsTest() throws Exception {
        when(dealService.readAllDeals(any(), any(), any()))
            .thenReturn(THREE_DEALS);
        MockHttpServletResponse response = mockMvc.perform(
                get("/fx-warehouse/v1/deals")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(status().isOk())
            .andReturn().getResponse();
        List<Deal> deals = DealMapper.jsonToListObject(mapper, response.getContentAsString());
        assertEquals(THREE_DEALS, deals);
    }

    @Test
    void getDealByIdTest() throws Exception {
        when(dealService.readDeal(JOD_DEAL.getId().toString()))
            .thenReturn(JOD_DEAL);
        MockHttpServletResponse response = mockMvc.perform(
                get(String.format("/fx-warehouse/v1/deals/%s", JOD_DEAL.getId()))
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
            )
            .andExpect(status().isOk())
            .andReturn().getResponse();
        Deal deals = DealMapper.jsonToSingleObject(mapper, response.getContentAsString());
        assertEquals(JOD_DEAL, deals);
    }
}