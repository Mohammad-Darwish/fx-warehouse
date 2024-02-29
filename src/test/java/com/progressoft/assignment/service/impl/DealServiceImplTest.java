package com.progressoft.assignment.service.impl;

import com.progressoft.assignment.domain.Currency;
import com.progressoft.assignment.dto.DealDto;
import com.progressoft.assignment.model.SaveDealsResponse;
import com.progressoft.assignment.repository.DealRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

import static com.progressoft.assignment.util.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DealServiceImplTest {

    @Mock
    private DealRepository dealRepository;

    @Mock
    private ModelMapper mapper;

    @InjectMocks
    private DealServiceImpl dealService;


    @Test
    void saveDealsTest() {
        // Setup
        when(dealRepository.existsById(JOD_DEAL.getId())).thenReturn(false);
        when(dealRepository.existsById(EUR_DEAL.getId())).thenReturn(true);
        when(dealRepository.saveAndFlush(JOD_DEAL)).thenReturn(JOD_DEAL);

        // Execute
        SaveDealsResponse saveDealsResponse = dealService.saveDeals(JOD_EUR_DEALS);

        // Assert
        assertEquals(JOD_DEAL.getId(), saveDealsResponse.savedDealsIds().get(0));
        assertEquals(EUR_DEAL.getId(), saveDealsResponse.existingDealIds().get(0));
    }

    @Test
    void readDealTest() {
        // Setup
        when(dealRepository.findById(JOD_DEAL.getId())).thenReturn(Optional.of(JOD_DEAL));
        when(mapper.map(JOD_DEAL, DealDto.class)).thenReturn(JOD_DEAL_DTO);

        // Execute
        DealDto dealDto = dealService.readDeal(JOD_DEAL.getId().toString());

        // Assert
        assertEquals(JOD_DEAL_DTO, dealDto);
    }

    @Test
    void readAllDealsTest() {
        // Setup
        when(dealRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(PAGE_TEST);
        when(mapper.map(JOD_DEAL, DealDto.class)).thenReturn(JOD_DEAL_DTO);
        when(mapper.map(JOD_DEAL, DealDto.class)).thenReturn(JOD_DEAL_DTO);

        // Execute
        List<DealDto> fetchedDeals = dealService.readAllDeals(Currency.JOD, AMOUNT_VALUE_10, AMOUNT_VALUE_1000);

        // Assert
        assertEquals(JOD_DEAL_DTO, fetchedDeals.get(0));
        assertEquals(1, fetchedDeals.size());
    }
}