package com.ministeam.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AdminDashboardDto {
    private long totalUsers;
    private long totalGames;
    private long totalPurchases;
    private double totalRevenue;
}
