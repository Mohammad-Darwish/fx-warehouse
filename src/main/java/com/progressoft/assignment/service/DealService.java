package com.progressoft.assignment.service;

import com.progressoft.assignment.domain.Currency;
import com.progressoft.assignment.dto.DealDto;
import com.progressoft.assignment.model.SaveDealsResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface DealService {

    public SaveDealsResponse saveDeals(List<DealDto> dealDtos);

    public DealDto readDeal(String id);

    public List<DealDto> readAllDeals(Currency currency, BigDecimal minValue, BigDecimal maxValue);
}
