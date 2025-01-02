package com.imanolortiz.games.service.impl;

import com.imanolortiz.games.commons.dtos.CreateGameDto;
import com.imanolortiz.games.commons.dtos.GameDto;
import com.imanolortiz.games.commons.dtos.UpdateGameDto;
import com.imanolortiz.games.commons.entities.GameModel;
import com.imanolortiz.games.repository.GameRepository;
import com.imanolortiz.games.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository){
        this.gameRepository = gameRepository;
    }

    @Override
    public List<GameDto> getAllGames() {
        return gameRepository
                .findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public GameDto createGame(CreateGameDto dto, String userId) {
        return Optional.of(dto)
                .map(parsedDto -> mapToEntity(parsedDto, userId))
                .map(gameRepository::save)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Error creating game"));
    }

    @Override
    public GameDto getGame(Long gameId) {
        return Optional.of(gameId)
                .flatMap(gameRepository::findById)
                .map(this::mapToDto)
                .orElseThrow(() -> new RuntimeException("Error retrieving game with id "+gameId));
    }

    @Override
    public void deleteGame(Long id, String userId) {
        gameRepository.findById(id)
                .map(existingGame -> {
                    if (!existingGame.getUserId().equals(userId)) {
                        throw new RuntimeException("Current user does not match game user");
                    }
                    return existingGame;
                })
                .orElseThrow(() -> new RuntimeException("Error retrieving game with id " + id));

        gameRepository.deleteById(id);
    }

    @Override
    public void updateGame(Long gameId, UpdateGameDto dto, String userId) {
        gameRepository.findById(gameId)
                .map(existingModel -> {
                    if(!existingModel.getUserId().equals(userId)){
                        throw new RuntimeException("Current user does not match game user");
                    }
                    existingModel.setName(dto.getName());
                    return gameRepository.save(existingModel);
                })
                .orElseThrow(() -> new RuntimeException("Error retrieving game with id "+gameId));
    }

    private GameModel mapToEntity(CreateGameDto dto, String userId){
        return GameModel.builder()
                .name(dto.getName())
                .userId(userId)
                .build();
    }

    private GameDto mapToDto(GameModel model){
        return GameDto.builder()
                .id(model.getId())
                .name(model.getName())
                .userId(model.getUserId())
                .build();
    }

}

