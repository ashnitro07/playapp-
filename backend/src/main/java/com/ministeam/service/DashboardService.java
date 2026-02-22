package com.ministeam.service;

import com.ministeam.dto.AdminDashboardDto;
import com.ministeam.dto.UserDashboardDto;
import com.ministeam.entity.Purchase;
import com.ministeam.repository.GameRepository;
import com.ministeam.repository.PurchaseRepository;
import com.ministeam.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service

public class DashboardService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    public UserDashboardDto getUserDashboard(String username) {
        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Purchase> purchases = purchaseRepository.findByUserId(user.getId());
        
        int totalGamesOwned = purchases.size();
        double totalMoneySpent = purchases.stream()
                .mapToDouble(p -> p.getGame().getPrice())
                .sum();

        return UserDashboardDto.builder()
                .totalGamesOwned(totalGamesOwned)
                .totalMoneySpent(totalMoneySpent)
                .build();
    }

    public AdminDashboardDto getAdminDashboard() {
        long totalUsers = userRepository.count();
        long totalGames = gameRepository.count();
        long totalPurchases = purchaseRepository.count();
        
        double totalRevenue = purchaseRepository.findAll().stream()
                .mapToDouble(p -> p.getGame().getPrice())
                .sum();

        return AdminDashboardDto.builder()
                .totalUsers(totalUsers)
                .totalGames(totalGames)
                .totalPurchases(totalPurchases)
                .totalRevenue(totalRevenue)
                .build();
    }
}
