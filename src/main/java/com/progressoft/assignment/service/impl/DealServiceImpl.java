package com.progressoft.assignment.service.impl;

import com.progressoft.assignment.domain.Currency;
import com.progressoft.assignment.domain.Deal;
import com.progressoft.assignment.dto.DealDto;
import com.progressoft.assignment.model.SaveDealsResponse;
import com.progressoft.assignment.repository.DealRepository;
import com.progressoft.assignment.service.DealService;
import com.progressoft.assignment.util.DealSpecification;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DealServiceImpl implements DealService {

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public SaveDealsResponse saveDeals(List<Deal> deals) {
        List<UUID> savedDeals = new ArrayList<>();
        List<UUID> existingDealIdentifiers = new ArrayList<>();

        for (Deal deal : deals) {
            if (!dealExists(deal)) {
                savedDeals.add(dealRepository.saveAndFlush(deal).getId());
            } else {
                existingDealIdentifiers.add(deal.getId());
            }
        }
        return new SaveDealsResponse(savedDeals, existingDealIdentifiers);
    }

    private boolean dealExists(Deal deal) {
        return dealRepository.existsById(deal.getId());
    }

    @Override
    public DealDto readDeal(String id) {
        return mapper.map(dealRepository.findById(UUID.fromString(id)).orElseThrow(), DealDto.class);
    }

    @Override
    public List<DealDto> readAllDeals(Currency currency,
                                      BigDecimal minAmount,
                                      BigDecimal maxAmount) {
        return dealRepository
            .findAll(
                DealSpecification.createSpecification(currency, minAmount, maxAmount),
                PageRequest.of(0, 10))
            .get()
            .map(deal -> mapper.map(deal, DealDto.class))
            .collect(Collectors.toList());
    }
}
