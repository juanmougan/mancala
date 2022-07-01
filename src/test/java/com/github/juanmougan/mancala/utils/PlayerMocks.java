package com.github.juanmougan.mancala.utils;

import static org.mockito.Mockito.when;

import com.github.juanmougan.mancala.models.Player;
import com.github.juanmougan.mancala.dtos.PlayerType;
import lombok.experimental.UtilityClass;
import org.mockito.Mockito;

@UtilityClass
public class PlayerMocks {

  public static Player mockPlayer(final PlayerType playerType) {
    final Player player = Mockito.mock(Player.class);
    when(player.getType()).thenReturn(playerType);
    return player;
  }
}
