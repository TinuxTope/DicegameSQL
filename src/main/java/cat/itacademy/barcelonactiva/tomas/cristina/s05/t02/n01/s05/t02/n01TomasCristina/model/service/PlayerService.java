package cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.service;

import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.domain.PlayerEntity;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto.GameDto;
import cat.itacademy.barcelonactiva.tomas.cristina.s05.t02.n01.s05.t02.n01TomasCristina.model.dto.PlayerDto;

import java.util.List;

public interface PlayerService {
    PlayerEntity createPlayer(PlayerDto playerDto);
    PlayerEntity updatePlayerName(int id, PlayerDto playerDto);
    GameDto rollDice(int playerId);
    void deletePlayerGames(int playerId);
    List<PlayerDto> getAllPlayers();
    List<GameDto> getPlayerGames(int playerId);
    double getAverageRanking();
    PlayerDto getLoser();
    PlayerDto getWinner();
}
