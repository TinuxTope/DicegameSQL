package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.service.impl;

import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.domain.GameEntity;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.domain.PlayerEntity;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto.GameDto;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto.PlayerDto;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class Impl {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

    public PlayerEntity createPlayer(PlayerDto playerDto) {
        PlayerEntity player = PlayerEntity.builder()
                .name(playerDto.getName())
                .email(playerDto.getEmail())
                .password(playerDto.getPassword())
                .build();
        return playerRepository.save(player);
    }

    public PlayerEntity updatePlayerName(int id, PlayerDto playerDto) {
        Optional<PlayerEntity> optionalPlayer = playerRepository.findById(id);
        if (optionalPlayer.isPresent()) {
            PlayerEntity player = optionalPlayer.get();
            player.setName(playerDto.getName());
            return playerRepository.save(player);
        } else {
            throw new RuntimeException("Player not found");
        }
    }

    public GameDto rollDice(int playerId) {
        Optional<PlayerEntity> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent()) {
            PlayerEntity player = optionalPlayer.get();
            Random random = new Random();
            int dice1 = random.nextInt(6) + 1;
            int dice2 = random.nextInt(6) + 1;
            boolean won = (dice1 + dice2) == 7;

            GameEntity game = GameEntity.builder()
                    .player(player)
                    .dice1(dice1)
                    .dice2(dice2)
                    .won(won)
                    .build();
            gameRepository.save(game);

            player.setTotalRolls(player.getTotalRolls() + 1);
            if (won) {
                player.setWonRolls(player.getWonRolls() + 1);
            }
            playerRepository.save(player);

            return toGameDto(game);
        } else {
            throw new RuntimeException("Player not found");
        }
    }

    public void deletePlayerGames(int playerId) {
        Optional<PlayerEntity> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent()) {
            PlayerEntity player = optionalPlayer.get();
            gameRepository.deleteByPlayer(player);
            player.setTotalRolls(0);
            player.setWonRolls(0);
            playerRepository.save(player);
        } else {
            throw new RuntimeException("Player not found");
        }
    }

    public List<PlayerDto> getAllPlayers() {
        List<PlayerEntity> players = playerRepository.findAll();
        return players.stream().map(this::toPlayerDto).collect(Collectors.toList());
    }

    public List<GameDto> getPlayerGames(int playerId) {
        Optional<PlayerEntity> optionalPlayer = playerRepository.findById(playerId);
        if (optionalPlayer.isPresent()) {
            PlayerEntity player = optionalPlayer.get();
            List<GameEntity> games = gameRepository.findByPlayer(player);
            return games.stream().map(this::toGameDto).collect(Collectors.toList());
        } else {
            throw new RuntimeException("Player not found");
        }
    }

    public double getAverageRanking() {
        List<PlayerEntity> players = playerRepository.findAll();
        if (players.isEmpty()) {
            return 0.0;
        }
        double totalSuccessRate = players.stream()
                .mapToDouble(this::calculateSuccessRate)
                .sum();
        return totalSuccessRate / players.size();
    }

    public PlayerDto getLoser() {
        List<PlayerEntity> players = playerRepository.findAll();
        return players.stream()
                .min(Comparator.comparingDouble(this::calculateSuccessRate))
                .map(this::toPlayerDto)
                .orElseThrow(() -> new RuntimeException("No players found"));
    }

    public PlayerDto getWinner() {
        List<PlayerEntity> players = playerRepository.findAll();
        return players.stream()
                .max(Comparator.comparingDouble(this::calculateSuccessRate))
                .map(this::toPlayerDto)
                .orElseThrow(() -> new RuntimeException("No players found"));
    }

    private double calculateSuccessRate(PlayerEntity player) {
        if (player.getTotalRolls() == 0) {
            return 0.0;
        }
        return (double) player.getWonRolls() / player.getTotalRolls() * 100;
    }

    private PlayerDto toPlayerDto(PlayerEntity player) {
        return new PlayerDto(
                player.getId(),
                player.getEmail(),
                player.getName(),
                player.getPassword(),
                player.getTotalRolls(),
                player.getWonRolls(),
                calculateSuccessRate(player)
        );
    }

    private GameDto toGameDto(GameEntity game) {
        return new GameDto(
                game.getId(),
                game.getDice1(),
                game.getDice2(),
                game.isWon(),
                game.getPlayer().getId()
        );
    }
}
