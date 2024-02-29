package com.progressoft.assignment.service.impl;

import com.progressoft.assignment.model.Currency;
import com.progressoft.assignment.model.Deal;
import com.progressoft.assignment.repository.DealRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
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

    @InjectMocks
    private DealServiceImpl dealService;

    @Test
    void saveDealsTest() {
        // Setup
        when(
            dealRepository.saveAllAndFlush(THREE_DEALS)
        ).thenReturn(THREE_DEALS);
        // Execute
        List<Deal> saveDeals = dealService.saveDeals(THREE_DEALS);
        // Assert
        assertEquals(THREE_DEALS, saveDeals);
    }

    @Test
    void readDealTest() {
        // Setup
        when(
            dealRepository.findById(JOD_DEAL.getId())
        ).thenReturn(Optional.of(JOD_DEAL));
        // Execute
        Deal deal = dealService.readDeal(JOD_DEAL.getId().toString());
        // Assert
        assertEquals(JOD_DEAL, deal);
    }

    @Test
    void readAllDealsTest() {
        // Setup
        when(dealRepository.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(PAGE_TEST);
        // Execute
        List<Deal> fetchedDeals = dealService.readAllDeals(Currency.JOD, AMOUNT_VALUE_10, AMOUNT_VALUE_1000);
        // Assert
        assertEquals(JOD_DEAL, fetchedDeals.get(0));
        assertEquals(1, fetchedDeals.size());
    }
}