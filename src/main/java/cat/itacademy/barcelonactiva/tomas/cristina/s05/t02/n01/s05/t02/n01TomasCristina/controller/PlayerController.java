package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.controller;

import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.domain.PlayerEntity;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto.GameDto;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto.PlayerDto;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.service.impl.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/players")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerEntity> createPlayer(@RequestBody PlayerDto playerDto) {
        PlayerEntity newPlayer = playerService.createPlayer(playerDto);
        return ResponseEntity.ok(newPlayer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlayerEntity> updatePlayerName(@PathVariable int id, @RequestBody PlayerDto playerDto) {
        PlayerEntity updatedPlayer = playerService.updatePlayerName(id, playerDto);
        return ResponseEntity.ok(updatedPlayer);
    }

    @PostMapping("/{id}/games")
    public ResponseEntity<GameDto> rollDice(@PathVariable int id) {
        GameDto gameResult = playerService.rollDice(id);
        return ResponseEntity.ok(gameResult);
    }

    @DeleteMapping("/{id}/games")
    public ResponseEntity<Void> deletePlayerGames(@PathVariable int id) {
        playerService.deletePlayerGames(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<PlayerDto>> getAllPlayers() {
        List<PlayerDto> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }

    @GetMapping("/{id}/games")
    public ResponseEntity<List<GameDto>> getPlayerGames(@PathVariable int id) {
        List<GameDto> games = playerService.getPlayerGames(id);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/ranking")
    public ResponseEntity<Double> getAverageRanking() {
        double averageRanking = playerService.getAverageRanking();
        return ResponseEntity.ok(averageRanking);
    }

    @GetMapping("/ranking/loser")
    public ResponseEntity<PlayerDto> getLoser() {
        PlayerDto loser = playerService.getLoser();
        return ResponseEntity.ok(loser);
    }

    @GetMapping("/ranking/winner")
    public ResponseEntity<PlayerDto> getWinner() {
        PlayerDto winner = playerService.getWinner();
        return ResponseEntity.ok(winner);
    }
}
