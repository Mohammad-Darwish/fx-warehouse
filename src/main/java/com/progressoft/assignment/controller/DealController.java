package com.progressoft.assignment.controller;


import com.progressoft.assignment.domain.Currency;
import com.progressoft.assignment.domain.Deal;
import com.progressoft.assignment.dto.DealDto;
import com.progressoft.assignment.model.SaveDealsResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

public interface DealController {

    @Operation(
        summary = "ADD deals rest API",
        description = "ADD deals endpoint to add deals to Database")
    @ApiResponse(
        responseCode = "201",
        description = "HTTP status is 201 created")
    @PostMapping(
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<SaveDealsResponse> addDeals(@Valid @RequestBody List<Deal> deals);

    @Operation(
        summary = "GET deals restAPI",
        description = "GET deals to get all deals with possibility to add filters using parameters")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP status code is 200 okay")
    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<DealDto>> getAllDeals(@Valid @RequestParam(required = false) Currency currency,
                                              @Valid @RequestParam(required = false) BigDecimal minValue,
                                              @Valid @RequestParam(required = false) BigDecimal maxValue);

    @Operation(
        summary = "GET deal by id restAPI",
        description = "GET deal by id to get deal id that matches the path variable")
    @ApiResponse(
        responseCode = "200",
        description = "HTTP status code is 200 okay")
    @GetMapping(
        path = "{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DealDto> getDealById(@PathVariable String id);
}
