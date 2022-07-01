package com.github.juanmougan.mancala.dtos;

import static com.github.juanmougan.mancala.utils.PlayerMocks.mockPlayer;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;
import org.junit.jupiter.api.Test;

class BoardTest {

  @Test
  void shouldSwitchPlayer() {
    final Player south = mockPlayer(PlayerType.SOUTH);
    final Player north = mockPlayer(PlayerType.NORTH);
    final Board board = Board.builder()
        .id(UUID.randomUUID())
        .south(south)
        .north(north)
        .currentPlayer(south)
        .build();

    board.switchPlayer();

    assertThat(board).extracting(Board::getCurrentPlayer).isEqualTo(north);
  }
}
