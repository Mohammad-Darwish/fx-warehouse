package com.progressoft.assignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Table(name = "deal")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Deal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "from-currency(Ordering Currency)", nullable = false)
    private Currency fromCurrency;
    @Column(name = "to-currency", nullable = false)
    private Currency toCurrency;

    @Column(name = "timestamp", nullable = false)
    private LocalDateTime timestamp;
    // fix the name of this column, It Does not look nice TODO
    @Column(name = "amount in Ordering Currency", nullable = false)
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
