package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.service;

import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto.GameDto;

import java.util.List;

public interface GameService {
    GameDto rollDice(int playerId);
    void deletePlayerGames(int playerId);
    List<GameDto> getPlayerGames(int playerId);
}
