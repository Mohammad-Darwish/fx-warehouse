package com.progressoft.assignment.service.impl;

import com.progressoft.assignment.model.Currency;
import com.progressoft.assignment.model.Deal;
import com.progressoft.assignment.repository.DealRepository;
import com.progressoft.assignment.service.DealService;
import com.progressoft.assignment.util.DealSpecification;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DealServiceImpl implements DealService {

    private DealRepository dealRepository;

    @Override
    public List<Deal> saveDeals(List<Deal> deals) {
        return dealRepository.saveAllAndFlush(deals);
    }

    @Override
    public Deal readDeal(String id) {
        return dealRepository.findById(UUID.fromString(id)).orElseThrow();
    }

    @Override
    public List<Deal> readAllDeals(Currency currency,
                                   BigDecimal minAmount,
                                   BigDecimal maxAmount) {
        return dealRepository
            .findAll(
                DealSpecification.createSpecification(currency, minAmount, maxAmount),
                PageRequest.of(0, 10))
            .get()
            .collect(Collectors.toList());
    }
}
