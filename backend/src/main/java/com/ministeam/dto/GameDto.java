package com.ministeam.dto;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class GameDto {
    private Long id;
    private String title;
    private String description;
    private String genre;
    private Double price;
    private LocalDate releaseDate;
    private Double rating;
}
