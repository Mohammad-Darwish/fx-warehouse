package com.progressoft.assignment.service;

import com.progressoft.assignment.model.Currency;
import com.progressoft.assignment.model.Deal;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DealServiceImpl implements DealService {
    @Override
    public Deal saveDeals(List<Deal> deals) {
        return null;
    }

    @Override
    public Deal readDeal(String id) {
        return null;
    }

    @Override
    public List<Deal> readAllDeals(Currency currency, BigDecimal minValue, BigDecimal maxValue) {
        return null;
    }
}
