package com.github.juanmougan.mancala.controllers;

import com.github.juanmougan.mancala.dtos.GameCreationRequest;
import com.github.juanmougan.mancala.dtos.GameResponse;
import com.github.juanmougan.mancala.dtos.MoveRequest;
import com.github.juanmougan.mancala.services.GameService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class GameController {

  private final GameService gameService;

  @PostMapping(Endpoints.GAMES)
  @ResponseStatus(HttpStatus.CREATED)
  public GameResponse create(@RequestBody GameCreationRequest request) {
    return gameService.create(request);
  }

  @PatchMapping(Endpoints.GAME_BY_ID)
  public GameResponse move(@PathVariable UUID id, @RequestBody MoveRequest request) {
    return gameService.move(id, request);
  }
}
