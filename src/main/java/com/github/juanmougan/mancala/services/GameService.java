package com.github.juanmougan.mancala.services;

import com.github.juanmougan.mancala.dtos.GameCreationRequest;
import com.github.juanmougan.mancala.dtos.GameResponse;
import com.github.juanmougan.mancala.dtos.MoveRequest;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {

  private final ModelMapper modelMapper = new ModelMapper();

  public GameResponse create(final GameCreationRequest request) {
    return null;
  }

  public GameResponse move(UUID gameId, MoveRequest moveRequest) {
    return null;
  }
}
