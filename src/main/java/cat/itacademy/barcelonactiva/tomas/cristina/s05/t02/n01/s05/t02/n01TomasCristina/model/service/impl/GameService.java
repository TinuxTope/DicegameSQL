package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.service.impl;

import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.domain.GameEntity;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.domain.PlayerEntity;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto.GameDto;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.repository.GameRepository;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class GameService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private GameRepository gameRepository;

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
