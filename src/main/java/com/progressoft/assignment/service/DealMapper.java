package com.progressoft.assignment.service;

import com.progressoft.assignment.domain.Deal;
import com.progressoft.assignment.dto.DealDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DealMapper {
    public DealDto fromEntity(Deal deal) {
        log.info("Mapping from Entity to DTO for Deal with ID: {}", deal.getId());
        return new DealDto(
            deal.getId(),
            deal.getFromCurrency(),
            deal.getToCurrency(),
            deal.getTimestamp(),
            deal.getAmount()
        );
    }

    public Deal toEntity(DealDto dealDTO) {
        log.info("Mapping from DTO to Entity for Deal with ID: {}", dealDTO.getId());
        return Deal.builder()
            .id(dealDTO.getId())
            .fromCurrency(dealDTO.getFromCurrency())
            .toCurrency(dealDTO.getToCurrency())
            .timestamp(dealDTO.getTimestamp())
            .amount(dealDTO.getAmount())
            .build();
    }
}
