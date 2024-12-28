package com.imanolortiz.games.service;

import com.imanolortiz.games.commons.dtos.CreateGameDto;
import com.imanolortiz.games.commons.dtos.GameDto;
import com.imanolortiz.games.commons.dtos.UpdateGameDto;

import java.util.List;

public interface GameService {
    List<GameDto> getAllGames();
    GameDto createGame(CreateGameDto dto);
    GameDto getGame(Long gameId);
    void deleteGame(Long id);
    void updateGame(Long id, UpdateGameDto dto);
}