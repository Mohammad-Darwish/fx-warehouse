package com.progressoft.assignment.service;

import com.progressoft.assignment.domain.Deal;
import com.progressoft.assignment.dto.DealDto;
import org.springframework.stereotype.Service;

@Service
public class DealMapper {
    public DealDto fromEntity(Deal deal) {
        return new DealDto(
            deal.getId(),
            deal.getFromCurrency(),
            deal.getToCurrency(),
            deal.getTimestamp(),
            deal.getAmount()
        );
    }

    public Deal toEntity(DealDto dealDTO) {
        return Deal.builder()
            .id(dealDTO.getId())
            .fromCurrency(dealDTO.getFromCurrency())
            .toCurrency(dealDTO.getToCurrency())
            .timestamp(dealDTO.getTimestamp())
            .amount(dealDTO.getAmount())
            .build();
    }
}
