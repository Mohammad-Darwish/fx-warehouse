package com.progressoft.assignment.service;

import com.progressoft.assignment.model.Deal;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface DealService {

    public Deal saveDeal(Deal deal);
    public Deal readDeal(UUID uuid);
}
