package com.github.juanmougan.mancala.services;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.juanmougan.mancala.dtos.PlayerType;
import com.github.juanmougan.mancala.models.Board;
import com.github.juanmougan.mancala.models.Cell;
import com.github.juanmougan.mancala.models.CellType;
import com.github.juanmougan.mancala.models.Player;
import com.github.juanmougan.mancala.utils.PlayerMocks;
import java.util.List;
import java.util.function.Supplier;
import org.junit.jupiter.api.Test;

class BoardServiceTest {

  @Test
  void shouldCreateInitialBoard() {
    final BoardService boardService = new BoardService();
    final Player south = PlayerMocks.mockPlayer(PlayerType.SOUTH);
    final Player north = PlayerMocks.mockPlayer(PlayerType.NORTH);

    final Board board = boardService.createInitialBoard(south, north);

    assertThat(board).extracting(Board::getNorth).isEqualTo(north);
    assertThat(board).extracting(Board::getSouth).isEqualTo(south);
    assertThat(board).extracting(Board::getCurrentPlayer).isEqualTo(south);
    validateCells(board::getSouthCells);
    validateCells(board::getNorthCells);
  }

  private void validateCells(final Supplier<List<Cell>> supplier) {
    assertThat(supplier.get())
        .filteredOn(c -> CellType.SCORING_WELL.equals(c.getType()))
        .extracting(Cell::getSeeds)
        .allMatch(s -> s == 0);
    assertThat(supplier.get())
        .filteredOn(c -> CellType.PIT.equals(c.getType()))
        .extracting(Cell::getSeeds)
        .allMatch(s -> s == 6);
  }

  @Test
  void shouldDistributeCells() {
    final BoardService boardService = new BoardService();
    final Player south = PlayerMocks.mockPlayer(PlayerType.SOUTH);
    final Player north = PlayerMocks.mockPlayer(PlayerType.NORTH);
    final Board board = boardService.createInitialBoard(south, north);
    board.setCurrentPlayer(south);

    boardService.distributeSeeds(0, board);

    assertThat(board.getNorthCells())
        .filteredOn(c -> c.getType().equals(CellType.PIT))
        .extracting(Cell::getSeeds)
        .allMatch(s -> s == 6);
    assertThat(board.getNorthCells())
        .filteredOn(c -> c.getType().equals(CellType.SCORING_WELL))
        .extracting(Cell::getSeeds)
        .allMatch(s -> s == 0);

    assertThat(board.getSouthCells())
        .filteredOn(c -> c.getType().equals(CellType.PIT))
        .extracting(Cell::getSeeds)
        .containsOnly(0, 7, 7, 7, 7, 7);
    assertThat(board.getSouthCells())
        .filteredOn(c -> c.getType().equals(CellType.SCORING_WELL))
        .extracting(Cell::getSeeds)
        .allMatch(s -> s == 1);
  }
}
