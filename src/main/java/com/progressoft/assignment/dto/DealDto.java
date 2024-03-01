package com.progressoft.assignment.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.progressoft.assignment.domain.Currency;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class DealDto {

    @Schema(description = "Deal Id")
    @NotNull(message = "Id must not be null")
    private UUID id;

    @Schema(description = "FX from Currency")
    @NotNull(message = "From currency field must not be null")
    private Currency fromCurrency;

    @Schema(description = "Deal to Currency")
    @NotNull(message = "To currency field must not be null")
    private Currency toCurrency;

    @Schema(description = "Deal date")
    @NotNull(message = "Timestamp must not be null")
    @PastOrPresent
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime timestamp;

    @Schema(description = "Deal amount")
    @Positive(message = "Amount must be positive")
    @NotNull(message = "Amount must not be null")
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
