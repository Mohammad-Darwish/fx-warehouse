package com.progressoft.assignment.controller;

import com.progressoft.assignment.model.Deal;
import com.progressoft.assignment.service.DealService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/fx-warehouse/v1/deals")
@RestController
public class DealController {

    private DealService dealService;

    @PostMapping
    public ResponseEntity<UUID> importRequest(@RequestBody Deal deal){
        Deal savedDeal = dealService.saveDeal(deal);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
