package com.progressoft.assignment.service;

import com.progressoft.assignment.model.Currency;
import com.progressoft.assignment.model.Deal;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface DealService {

    public Deal saveDeals(List<Deal> deals);

    public Deal readDeal(String id);

    public List<Deal> readAllDeals(Currency currency, BigDecimal minValue, BigDecimal maxValue);
}
