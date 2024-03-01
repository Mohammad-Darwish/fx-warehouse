package com.progressoft.assignment.util;

import com.progressoft.assignment.domain.Currency;
import com.progressoft.assignment.domain.Deal;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

@UtilityClass
public class DealSpecification {

    public static Specification<Deal> hasCurrency(Currency currency) {
        return (root, query, criteriaBuilder) ->
            currency == null ? null : criteriaBuilder.equal(root.get("fromCurrency"), currency);
    }

    public static Specification<Deal> hasMinAmount(BigDecimal minAmount) {
        return (root, query, criteriaBuilder) ->
            minAmount == null ? null : criteriaBuilder.greaterThan(root.get("amount"), minAmount);
    }

    public static Specification<Deal> hasMaxAmount(BigDecimal maxAmount) {
        return (root, query, criteriaBuilder) ->
            maxAmount == null ? null : criteriaBuilder.lessThan(root.get("amount"), maxAmount);
    }

    public static Specification<Deal> createSpecification(Currency currency, BigDecimal minAmount, BigDecimal maxAmount) {
        Specification<Deal> spec = Specification.where(null);

        if (currency != null) {
            spec = spec.and(hasCurrency(currency));
        }
        if (minAmount != null) {
            spec = spec.and(hasMinAmount(minAmount));
        }
        if (maxAmount != null) {
            spec = spec.and(hasMaxAmount(maxAmount));
        }

        return spec;
    }
}
