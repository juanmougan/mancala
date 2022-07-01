package com.github.juanmougan.mancala.services;

import com.github.juanmougan.mancala.dtos.Player;
import com.github.juanmougan.mancala.dtos.PlayerType;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PlayerService {

  public Player create(final String name, final PlayerType type) {
    return Player.builder()
        .id(UUID.randomUUID())
        .name(name)
        .type(type)
        .build();
  }

}
