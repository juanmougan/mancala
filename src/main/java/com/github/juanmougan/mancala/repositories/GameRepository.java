package com.github.juanmougan.mancala.repositories;

import com.github.juanmougan.mancala.models.Game;
import java.util.Optional;
import java.util.UUID;

public interface GameRepository {

  // TODO use Spring Data here
  Optional<Game> findById(UUID id);

}
