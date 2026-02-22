package com.ministeam.service;

import com.ministeam.dto.PurchaseDto;
import com.ministeam.entity.Game;
import com.ministeam.entity.Purchase;
import com.ministeam.entity.User;
import com.ministeam.repository.GameRepository;
import com.ministeam.repository.PurchaseRepository;
import com.ministeam.repository.UserRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public PurchaseDto purchaseGame(String username, Long gameId) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (purchaseRepository.existsByUserIdAndGameId(user.getId(), gameId)) {
            throw new RuntimeException("You already own this game!");
        }

        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new RuntimeException("Game not found"));

        Purchase purchase = Purchase.builder()
                .user(user)
                .game(game)
                .purchaseDate(LocalDateTime.now())
                .build();

        purchase = purchaseRepository.save(purchase);

        return PurchaseDto.builder()
                .id(purchase.getId())
                .gameId(game.getId())
                .gameTitle(game.getTitle())
                .purchasePrice(game.getPrice())
                .purchaseDate(purchase.getPurchaseDate())
                .build();
    }

    public List<PurchaseDto> getUserLibrary(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return purchaseRepository.findByUserId(user.getId()).stream()
                .map(purchase -> PurchaseDto.builder()
                        .id(purchase.getId())
                        .gameId(purchase.getGame().getId())
                        .gameTitle(purchase.getGame().getTitle())
                        .purchasePrice(purchase.getGame().getPrice())
                        .purchaseDate(purchase.getPurchaseDate())
                        .build())
                .collect(Collectors.toList());
    }
}
