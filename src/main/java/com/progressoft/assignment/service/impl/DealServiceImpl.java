package com.progressoft.assignment.service.impl;

import com.progressoft.assignment.domain.Currency;
import com.progressoft.assignment.domain.Deal;
import com.progressoft.assignment.dto.DealDto;
import com.progressoft.assignment.exception.ResourceCreationFailedException;
import com.progressoft.assignment.exception.ResourceNotFoundException;
import com.progressoft.assignment.model.SaveDealsResponse;
import com.progressoft.assignment.repository.DealRepository;
import com.progressoft.assignment.service.DealMapper;
import com.progressoft.assignment.service.DealService;
import com.progressoft.assignment.util.DealSpecification;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class DealServiceImpl implements DealService {

    @Autowired
    private DealRepository dealRepository;

    @Autowired
    private DealMapper mapper;

    @Override
    public SaveDealsResponse saveDeals(List<DealDto> dealDtos) {
        log.info("Attempting to save a batch of deals, size: {}", dealDtos.size());
        List<UUID> savedDeals = new ArrayList<>();
        List<UUID> existingDealIdentifiers = new ArrayList<>();

        for (DealDto dealDto : dealDtos) {
            Deal deal = mapper.toEntity(dealDto);
            try {
                if (!dealExists(deal)) {
                    UUID savedDealId = dealRepository.saveAndFlush(deal).getId();
                    savedDeals.add(savedDealId);
                    log.debug("Saved a new deal with id: {}", savedDealId);
                } else {
                    existingDealIdentifiers.add(deal.getId());
                    log.debug("Deal already exists with id: {}", deal.getId());
                }
            } catch (IllegalArgumentException e) {
                log.error("Could not save a deal with id: {}", deal.getId(), e);
                throw new ResourceCreationFailedException(deal.getId().toString());
            }
        }

        log.info("Save deals completed. New deals saved: {}, Existing deals skipped: {}",
            savedDeals.size(),
            existingDealIdentifiers.size());
        return new SaveDealsResponse(savedDeals, existingDealIdentifiers);
    }
    private boolean dealExists(Deal deal) {
        return dealRepository.existsById(deal.getId());
    }

    @Override
    public DealDto readDeal(String id) {
        log.info("Attempting to read a deal with id: {}", id);
        Deal deal = dealRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> {
                log.warn("Deal not found with id: {}", id);
                return new ResourceNotFoundException(id);
            });
        log.debug("Deal found with id: {}", id);
        return mapper.fromEntity(deal);
    }

    @Override
    public List<DealDto> readAllDeals(Currency currency, BigDecimal minAmount, BigDecimal maxAmount) {
        log.info("Reading all deals with currency: {}, minAmount: {}, maxAmount: {}", currency, minAmount, maxAmount);
        List<DealDto> dealDtos = dealRepository
            .findAll(
                DealSpecification.createSpecification(currency, minAmount, maxAmount),
                PageRequest.of(0, 10))
            .get()
            .map(deal -> mapper.fromEntity(deal))
            .collect(Collectors.toList());
        log.info("Found {} deals matching the criteria", dealDtos.size());
        return dealDtos;
    }
}
