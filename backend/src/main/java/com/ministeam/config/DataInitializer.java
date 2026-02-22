package com.ministeam.config;

import com.ministeam.entity.Game;
import com.ministeam.entity.Role;
import com.ministeam.entity.User;
import com.ministeam.repository.GameRepository;
import com.ministeam.repository.UserRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

@Component

public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Create Admin and User if they don't exist
        if (!userRepository.existsByUsername("admin")) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .role(Role.ADMIN)
                    .build();
            userRepository.save(admin);
        }

        if (!userRepository.existsByUsername("user")) {
            User user = User.builder()
                    .username("user")
                    .password(passwordEncoder.encode("user123"))
                    .role(Role.USER)
                    .build();
            userRepository.save(user);
        }

        // Create some sample games if catalog is empty
        if (gameRepository.count() == 0) {
            List<Game> sampleGames = List.of(
                    Game.builder()
                            .title("Cyber Steampunk 2099")
                            .description("Open-world, action-adventure story set in Night City.")
                            .genre("RPG")
                            .price(59.99)
                            .releaseDate(LocalDate.of(2020, 12, 10))
                            .rating(4.5)
                            .build(),
                    Game.builder()
                            .title("Elden Ringz")
                            .description("Action role-playing game in a vast open world.")
                            .genre("RPG")
                            .price(59.99)
                            .releaseDate(LocalDate.of(2022, 2, 25))
                            .rating(4.9)
                            .build(),
                    Game.builder()
                            .title("Valley" + " of Stars")
                            .description("An open-ended country-life RPG!")
                            .genre("Simulation")
                            .price(14.99)
                            .releaseDate(LocalDate.of(2016, 2, 26))
                            .rating(4.8)
                            .build(),
                    Game.builder()
                            .title("Counter-Coup 2")
                            .description("Tactical first-person shooter.")
                            .genre("FPS")
                            .price(0.0) // Free to play
                            .releaseDate(LocalDate.of(2023, 9, 27))
                            .rating(4.2)
                            .build()
            );
            gameRepository.saveAll(sampleGames);
        }
    }
}
