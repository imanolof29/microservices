package com.imanolortiz.games.repository;

import com.imanolortiz.games.commons.entities.GameModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<GameModel, Long> {
}

