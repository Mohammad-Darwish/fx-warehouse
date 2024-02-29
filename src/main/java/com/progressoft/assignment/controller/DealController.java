package com.progressoft.assignment.controller;

import com.progressoft.assignment.dto.DealDto;
import com.progressoft.assignment.model.Currency;
import com.progressoft.assignment.model.Deal;
import com.progressoft.assignment.pojo.SaveDealsResponse;
import com.progressoft.assignment.service.DealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@RequestMapping("/fx-warehouse/v1/deals")
@RestController
public class DealController {

    private DealService dealService;

    @Operation(
        summary = "ADD deals rest API",
        description = "ADD deals endpoint to add deals to Database")
    @ApiResponse(
        responseCode = "201",
        description = "HTTP status is 201 created")
    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaveDealsResponse> addDeals(@Valid @RequestBody List<Deal> deals) {
        SaveDealsResponse savedDeals = dealService.saveDeals(deals);
        if (savedDeals.existingDealIds().isEmpty()) {
            return new ResponseEntity<>(savedDeals, HttpStatus.CREATED);
        } else if (savedDeals.savedDealsIds().isEmpty()) {
            return new ResponseEntity<>(savedDeals, HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(savedDeals, HttpStatus.OK);
    }

    @Operation(
        summary = "GET deals restAPI",
        description = "GET deals to get all deals with possibility to add filters using parameters")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP status code is 200 okay")
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DealDto>> getAllDeals(@Valid @RequestParam(required = false) Currency currency,
                                                     @Valid @RequestParam(required = false) BigDecimal minValue,
                                                     @Valid @RequestParam(required = false) BigDecimal maxValue) {
        List<DealDto> deals = dealService.readAllDeals(currency, minValue, maxValue);
        return new ResponseEntity<>(deals, HttpStatus.OK);
    }

    @Operation(
        summary = "GET deal by id restAPI",
        description = "GET deal by id to get deal id that matches the path variable")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP status code is 200 okay")
    @GetMapping(
        path = "{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DealDto> getDealById(@PathVariable String id) {
        DealDto dealDto = dealService.readDeal(id);
        return new ResponseEntity<>(dealDto, HttpStatus.OK);
    }
}
