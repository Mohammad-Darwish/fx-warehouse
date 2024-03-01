package com.progressoft.assignment.service;

import com.progressoft.assignment.domain.Deal;
import com.progressoft.assignment.dto.DealDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.progressoft.assignment.util.Constants.JOD_DEAL;
import static com.progressoft.assignment.util.Constants.JOD_DEAL_DTO;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class DealMapperTest {

    @InjectMocks
    private DealMapper dealMapper;

    @Test
    void fromEntityTest() {
        // Execute
        DealDto dealDto = dealMapper.fromEntity(JOD_DEAL);
        // Assert
        assertEquals(JOD_DEAL.getId(), dealDto.getId());
        assertEquals(JOD_DEAL.getFromCurrency(), dealDto.getFromCurrency());
        assertEquals(JOD_DEAL.getToCurrency(), dealDto.getToCurrency());
        assertEquals(JOD_DEAL.getTimestamp(), dealDto.getTimestamp());
        assertEquals(JOD_DEAL.getAmount(), dealDto.getAmount());
    }

    @Test
    void toEntityTest() {
        // Execute
        Deal deal = dealMapper.toEntity(JOD_DEAL_DTO);
        // Assert
        assertEquals(JOD_DEAL_DTO.getId(), deal.getId());
        assertEquals(JOD_DEAL_DTO.getFromCurrency(), deal.getFromCurrency());
        assertEquals(JOD_DEAL_DTO.getToCurrency(), deal.getToCurrency());
        assertEquals(JOD_DEAL_DTO.getTimestamp(), deal.getTimestamp());
        assertEquals(JOD_DEAL_DTO.getAmount(), deal.getAmount());
    }
}