package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.controller;

import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto.GameDto;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.service.impl.GameServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {
    private final GameServiceImpl gameServiceImpl;

    public GameController(GameServiceImpl gameServiceImpl) {
        this.gameServiceImpl = gameServiceImpl;
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<List<GameDto>> getPlayerGames(@PathVariable int playerId) {
        List<GameDto> games = gameServiceImpl.getPlayerGames(playerId);
        return ResponseEntity.ok(games);
    }

    @DeleteMapping("/{playerId}/delete")
    public ResponseEntity<Void> deletePlayerGames(@PathVariable int playerId) {
        gameServiceImpl.deletePlayerGames(playerId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{playerId}/roll")
    public ResponseEntity<GameDto> rollDice(@PathVariable int playerId) {
        GameDto gameResult = gameServiceImpl.rollDice(playerId);
        return ResponseEntity.ok(gameResult);
    }
}
