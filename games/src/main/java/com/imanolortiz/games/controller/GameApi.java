package com.imanolortiz.games.controller;

import com.imanolortiz.games.commons.constants.ApiPathConstants;
import com.imanolortiz.games.commons.dtos.CreateGameDto;
import com.imanolortiz.games.commons.dtos.GameDto;
import com.imanolortiz.games.commons.dtos.UpdateGameDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiPathConstants.V1_ROUTE + ApiPathConstants.GAME_ROUTE)
public interface GameApi {

    @GetMapping(value = "")
    ResponseEntity<List<GameDto>> listGames();

    @PostMapping(value = "create")
    ResponseEntity<GameDto> createGame(@RequestBody CreateGameDto dto, @RequestHeader("userIdRequest") String userId);

    @GetMapping(value = "/{gameId}")
    ResponseEntity<GameDto> getGame(@PathVariable Long gameId);

    @DeleteMapping(value = "/{gameId}")
    ResponseEntity<Void> deleteGame(@PathVariable Long gameId, @RequestHeader("userIdRequest") String userId);

    @PutMapping(value = "/{gameId}")
    ResponseEntity<Void> updateGame(@PathVariable Long gameId, @RequestBody UpdateGameDto dto, @RequestHeader("userIdRequest") String userId);

}