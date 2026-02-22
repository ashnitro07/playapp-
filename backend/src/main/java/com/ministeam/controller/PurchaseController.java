package com.ministeam.controller;

import com.ministeam.dto.PurchaseDto;
import com.ministeam.service.PurchaseService;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchases")

public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/{gameId}")
    public ResponseEntity<PurchaseDto> purchaseGame(Authentication authentication, @PathVariable Long gameId) {
        String username = authentication.getName();
        return ResponseEntity.ok(purchaseService.purchaseGame(username, gameId));
    }

    @GetMapping("/library")
    public ResponseEntity<List<PurchaseDto>> getMyLibrary(Authentication authentication) {
        String username = authentication.getName();
        return ResponseEntity.ok(purchaseService.getUserLibrary(username));
    }
}
