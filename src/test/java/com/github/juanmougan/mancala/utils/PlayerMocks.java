package com.github.juanmougan.mancala.utils;

import static com.github.juanmougan.mancala.dtos.PlayerType.NORTH;
import static com.github.juanmougan.mancala.dtos.PlayerType.SOUTH;
import static org.mockito.Mockito.when;

import com.github.juanmougan.mancala.dtos.PlayerDto;
import com.github.juanmougan.mancala.dtos.PlayerType;
import com.github.juanmougan.mancala.models.Player;
import java.util.UUID;
import lombok.experimental.UtilityClass;
import org.mockito.Mockito;

@UtilityClass
public class PlayerMocks {

  public static Player mockPlayer(final PlayerType playerType) {
    final Player player = Mockito.mock(Player.class);
    when(player.getType()).thenReturn(playerType);
    return player;
  }

  public static PlayerDto mockSouthPlayer() {
    return PlayerDto.builder()
        .id(UUID.randomUUID())
        .name("south")
        .type(SOUTH)
        .build();
  }

  public static PlayerDto mockNorthPlayer() {
    return PlayerDto.builder()
        .id(UUID.randomUUID())
        .name("north")
        .type(NORTH)
        .build();
  }
}
