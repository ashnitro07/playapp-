package com.ministeam.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class PurchaseDto {
    private Long id;
    private Long gameId;
    private String gameTitle;
    private Double purchasePrice;
    private LocalDateTime purchaseDate;
}
