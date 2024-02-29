package com.progressoft.assignment.dto;

import com.progressoft.assignment.model.Currency;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@Builder
@Getter
public class DealDto {

    @NotNull(message = "Id must not be null")
    private UUID id;

    @NotNull(message = "From currency field must not be null or empty")
    private Currency fromCurrency;

    @NotNull(message = "To currency field must not be null or empty")
    private Currency toCurrency;

    @NotNull(message = "Timestamp must not be null or empty")
    @PastOrPresent
    private LocalDateTime timestamp;

    @NotNull
    @Positive(message = "Amount must not be null, empty, zero, or minus")
    private BigDecimal amount;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DealDto dealDto = (DealDto) o;

        return Objects.equals(id, dealDto.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
