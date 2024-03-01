package com.progressoft.assignment.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deal")
@Entity(name = "deal")
public class Deal {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @NotNull
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "from_currency_Ordering_Currency", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Currency fromCurrency;

    @Enumerated(EnumType.STRING)
    @Column(name = "to_currency", nullable = false)
    @NotNull
    private Currency toCurrency;

    @Column(name = "timestamp", nullable = false)
    @PastOrPresent
    @NotNull
    private LocalDateTime timestamp;

    // fix the name of this column, It Does not look nice TODO
    @Column(name = "amount_in_ordering_currency", nullable = false)
    @NotNull
    @Positive
    private BigDecimal amount;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Deal deal = (Deal) o;

        return Objects.equals(id, deal.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
