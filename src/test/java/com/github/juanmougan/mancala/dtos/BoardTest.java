package com.github.juanmougan.mancala.dtos;

import static com.github.juanmougan.mancala.utils.BoardMocks.mockRealBoard;
import static com.github.juanmougan.mancala.utils.PlayerMocks.mockPlayer;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.juanmougan.mancala.models.Board;
import com.github.juanmougan.mancala.models.Cell;
import com.github.juanmougan.mancala.models.Player;
import java.util.List;
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

  @Test
  void shouldReturnCellsForPlayer() {
    final Board board = mockRealBoard();
    board.setCurrentPlayer(board.getSouth());

    final List<Cell> southCells = board.getCellsForPlayer(board.getSouth());

    assertThat(southCells).allMatch(c -> c.getOwner().equals(board.getSouth()));
  }

  @Test
  void shouldReturnCellsForRival() {
    final Board board = mockRealBoard();
    board.setCurrentPlayer(board.getSouth());

    final List<Cell> southCells = board.getCellsForRival(board.getNorth());

    assertThat(southCells).allMatch(c -> c.getOwner().equals(board.getSouth()));
  }
}
