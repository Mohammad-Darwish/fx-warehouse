package com.progressoft.assignment.util;

import com.progressoft.assignment.domain.Currency;
import com.progressoft.assignment.domain.Deal;
import com.progressoft.assignment.dto.DealDto;
import com.progressoft.assignment.model.SaveDealsResponse;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class Constants {

    public static final Deal JOD_DEAL = Deal.builder()
        .id(UUID.randomUUID())
        .fromCurrency(Currency.JOD)
        .toCurrency(Currency.EUR)
        .amount(new BigDecimal(100))
        .timestamp(LocalDateTime.now())
        .build();

    public static final Deal EUR_DEAL = Deal.builder()
        .id(UUID.randomUUID())
        .fromCurrency(Currency.EUR)
        .toCurrency(Currency.USD)
        .amount(new BigDecimal("100000.155"))
        .timestamp(LocalDateTime.now())
        .build();

    public static final Deal USD_DEAL = Deal.builder()
        .id(UUID.randomUUID())
        .fromCurrency(Currency.USD)
        .toCurrency(Currency.JOD)
        .amount(new BigDecimal("175434656.13333"))
        .timestamp(LocalDateTime.now())
        .build();

    public static final List<Deal> JOD_EUR_USD_DEALS = List.of(JOD_DEAL, EUR_DEAL, USD_DEAL);
    public static final List<Deal> JOD_EUR_DEALS = List.of(JOD_DEAL, EUR_DEAL);

    public static final DealDto JOD_DEAL_DTO = DealDto.builder()
        .id(UUID.randomUUID())
        .fromCurrency(Currency.JOD)
        .toCurrency(Currency.EUR)
        .amount(new BigDecimal(100))
        .timestamp(LocalDateTime.now())
        .build();

    public static final DealDto EUR_DEAL_DTO = DealDto.builder()
        .id(UUID.randomUUID())
        .fromCurrency(Currency.EUR)
        .toCurrency(Currency.USD)
        .amount(new BigDecimal("100000.155"))
        .timestamp(LocalDateTime.now())
        .build();

    public static final DealDto USD_DEAL_DTO = DealDto.builder()
        .id(UUID.randomUUID())
        .fromCurrency(Currency.USD)
        .toCurrency(Currency.JOD)
        .amount(new BigDecimal("175434656.13333"))
        .timestamp(LocalDateTime.now())
        .build();

    public static final List<DealDto> JOD_EUR_USD_DEALS_DTO = List.of(JOD_DEAL_DTO, EUR_DEAL_DTO, USD_DEAL_DTO);
    public static final List<DealDto> JOD_EUR_DEALS_DTO = List.of(JOD_DEAL_DTO, EUR_DEAL_DTO);

    public static final SaveDealsResponse SAVE_DEALS_RESPONSE =
        new SaveDealsResponse(List.of(JOD_DEAL.getId()), List.of(USD_DEAL.getId(), EUR_DEAL.getId()));
    public static final SaveDealsResponse SAVE_DEALS_RESPONSE_ALREADY_EXIST =
        new SaveDealsResponse(List.of(), List.of(JOD_DEAL.getId(), EUR_DEAL.getId()));
    public static final SaveDealsResponse SAVE_DEALS_RESPONSE_NEW =
        new SaveDealsResponse(List.of(JOD_DEAL.getId(), EUR_DEAL.getId()), List.of());

    public static BigDecimal AMOUNT_VALUE_10 = new BigDecimal(10);
    public static BigDecimal AMOUNT_VALUE_1000 = new BigDecimal(1000);

//    public static Specification<Deal> DEAL_SPECIFICATION_TEST = DealSpecification
//        .createSpecification(Currency.JOD, AMOUNT_VALUE_10, AMOUNT_VALUE_1000);

    public static Page<Deal> PAGE_TEST = new PageImpl<>(List.of(JOD_DEAL));
}
