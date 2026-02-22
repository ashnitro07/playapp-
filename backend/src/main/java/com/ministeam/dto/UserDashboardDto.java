package com.ministeam.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDashboardDto {
    private int totalGamesOwned;
    private double totalMoneySpent;
}
