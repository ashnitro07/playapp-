package com.ministeam.repository;

import com.ministeam.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByUserId(Long userId);
    boolean existsByUserIdAndGameId(Long userId, Long gameId);
}
