package com.progressoft.assignment.controller;

import com.progressoft.assignment.domain.Currency;
import com.progressoft.assignment.domain.Deal;
import com.progressoft.assignment.dto.DealDto;
import com.progressoft.assignment.model.SaveDealsResponse;
import com.progressoft.assignment.service.DealService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/fx-warehouse/v1/deals")
@RestController
public class DealControllerImpl implements DealController {

    private DealService dealService;

    @Override
    public ResponseEntity<SaveDealsResponse> addDeals(@Valid @RequestBody List<Deal> deals) {
        SaveDealsResponse savedDeals = dealService.saveDeals(deals);
        if (savedDeals.existingDealIds().isEmpty()) {
            return new ResponseEntity<>(savedDeals, HttpStatus.CREATED);
        } else if (savedDeals.savedDealsIds().isEmpty()) {
            return new ResponseEntity<>(savedDeals, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(savedDeals, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<DealDto>> getAllDeals(@Valid @RequestParam(required = false) Currency currency,
                                                     @Valid @RequestParam(required = false) BigDecimal minValue,
                                                     @Valid @RequestParam(required = false) BigDecimal maxValue) {
        List<DealDto> deals = dealService.readAllDeals(currency, minValue, maxValue);
        return new ResponseEntity<>(deals, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DealDto> getDealById(@PathVariable String id) {
        DealDto dealDto = dealService.readDeal(id);
        return new ResponseEntity<>(dealDto, HttpStatus.OK);
    }
}
