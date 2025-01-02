package com.imanolortiz.games.controller.impl;

import com.imanolortiz.games.commons.dtos.CreateGameDto;
import com.imanolortiz.games.commons.dtos.GameDto;
import com.imanolortiz.games.commons.dtos.UpdateGameDto;
import com.imanolortiz.games.controller.GameApi;
import com.imanolortiz.games.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GameController implements GameApi {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @Override
    public ResponseEntity<List<GameDto>> listGames() {
        return ResponseEntity.ok(gameService.getAllGames());
    }

    @Override
    public ResponseEntity<GameDto> createGame(CreateGameDto dto, String userId) {
        return ResponseEntity.ok(gameService.createGame(dto, userId));
    }

    @Override
    public ResponseEntity<GameDto> getGame(Long gameId) {
        return ResponseEntity.ok(gameService.getGame(gameId));
    }

    @Override
    public ResponseEntity<Void> deleteGame(Long gameId, String userId) {
        gameService.deleteGame(gameId, userId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateGame(Long gameId, @RequestBody UpdateGameDto dto, String userId) {
        gameService.updateGame(gameId, dto, userId);
        return ResponseEntity.noContent().build();
    }
}
