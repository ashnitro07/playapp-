package com.ministeam.service;

import com.ministeam.dto.GameDto;
import com.ministeam.entity.Game;
import com.ministeam.repository.GameRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<GameDto> getAllGames() {
        return gameRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public GameDto getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        return mapToDto(game);
    }

    public GameDto createGame(GameDto gameDto) {
        Game game = Game.builder()
                .title(gameDto.getTitle())
                .description(gameDto.getDescription())
                .genre(gameDto.getGenre())
                .price(gameDto.getPrice())
                .releaseDate(gameDto.getReleaseDate())
                .rating(gameDto.getRating())
                .build();
        return mapToDto(gameRepository.save(game));
    }
    
    public GameDto updateGame(Long id, GameDto gameDto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        
        game.setTitle(gameDto.getTitle());
        game.setDescription(gameDto.getDescription());
        game.setGenre(gameDto.getGenre());
        game.setPrice(gameDto.getPrice());
        game.setReleaseDate(gameDto.getReleaseDate());
        game.setRating(gameDto.getRating());
        
        return mapToDto(gameRepository.save(game));
    }
    
    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Game not found"));
        gameRepository.delete(game);
    }

    private GameDto mapToDto(Game game) {
        return GameDto.builder()
                .id(game.getId())
                .title(game.getTitle())
                .description(game.getDescription())
                .genre(game.getGenre())
                .price(game.getPrice())
                .releaseDate(game.getReleaseDate())
                .rating(game.getRating())
                .build();
    }
}
