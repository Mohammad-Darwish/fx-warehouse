package com.progressoft.assignment.util;

import com.progressoft.assignment.model.Currency;
import com.progressoft.assignment.model.Deal;
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

    public static final List<Deal> THREE_DEALS = List.of(JOD_DEAL, EUR_DEAL, USD_DEAL);

    public static BigDecimal AMOUNT_VALUE_10 = new BigDecimal(10);
    public static BigDecimal AMOUNT_VALUE_1000 = new BigDecimal(1000);

//    public static Specification<Deal> DEAL_SPECIFICATION_TEST = DealSpecification
//        .createSpecification(Currency.JOD, AMOUNT_VALUE_10, AMOUNT_VALUE_1000);

    public static Page<Deal> PAGE_TEST = new PageImpl<>(List.of(JOD_DEAL));
}
