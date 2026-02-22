package com.ministeam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "games")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String genre;

    @Column(nullable = false)
    private Double price;

    private LocalDate releaseDate;

    // Optional: could be average rating stored here, or calculated dynamically
    private Double rating;
}
