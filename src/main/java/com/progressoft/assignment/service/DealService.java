package com.progressoft.assignment.service;

import com.progressoft.assignment.dto.DealDto;
import com.progressoft.assignment.model.Currency;
import com.progressoft.assignment.model.Deal;
import com.progressoft.assignment.pojo.SaveDealsResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface DealService {

    public SaveDealsResponse saveDeals(List<Deal> deals);

    public DealDto readDeal(String id);

    public List<DealDto> readAllDeals(Currency currency, BigDecimal minValue, BigDecimal maxValue);
}
