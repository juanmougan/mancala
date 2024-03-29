package com.github.juanmougan.mancala.services;

import static java.util.stream.Collectors.toList;

import com.github.juanmougan.mancala.models.Board;
import com.github.juanmougan.mancala.models.Cell;
import com.github.juanmougan.mancala.models.CellType;
import com.github.juanmougan.mancala.models.Player;
import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BoardService {

  public static final int INITIAL_SEEDS = 6;

  public Board createInitialBoard(Player south, Player north) {
    return Board.builder()
        .id(UUID.randomUUID())
        .south(south)
        .north(north)
        .currentPlayer(south)
        .southCells(createCellsForPlayer(south))
        .northCells(createCellsForPlayer(north))
        .build();
  }

  private List<Cell> createCellsForPlayer(final Player player) {
    final Stream<Cell> pitStream = IntStream.rangeClosed(0, 5).mapToObj(i -> Cell.builder()
        .id(UUID.randomUUID())
        .owner(player)
        .seeds(INITIAL_SEEDS)
        .type(CellType.PIT)
        .build());
    final Stream<Cell> scoringWellStream = Stream.of(Cell.builder()
        .id(UUID.randomUUID())
        .owner(player)
        .seeds(0)
        .type(CellType.SCORING_WELL)
        .build());
    return Stream.concat(pitStream, scoringWellStream).collect(toList());
  }

  public void distributeSeeds(final int pit, final Board board) {
    final List<Cell> cellsForPlayer = board.getCellsForPlayer(board.getCurrentPlayer());
    final Cell startingCell = cellsForPlayer.get(pit);
    int seedsInStartingPit = startingCell.extractSeeds();
    incrementSeedsInSubsequentCells(pit, board, seedsInStartingPit);
  }

  private void incrementSeedsInSubsequentCells(final int startingPit, final Board board,
      final int totalSeeds) {
    int remainingSeeds = totalSeeds;
    // TODO refactor this, and consider what happens if there are enough seeds to return to current player's board again!
    final List<Cell> cellsForPlayer = board.getCellsForPlayer(board.getCurrentPlayer());
    for (int i = startingPit + 1; i < cellsForPlayer.size(); i++) {
      if (remainingSeeds > 0) {
        cellsForPlayer.get(i).addOneSeed(board.getCurrentPlayer());
        remainingSeeds--;
      }
    }
    final List<Cell> cellsForRival = board.getCellsForRival(board.getCurrentPlayer());
    for (int i = 0; i < cellsForRival.size(); i++) {
      if (remainingSeeds > 0) {
        cellsForRival.get(i).addOneSeed(board.getCurrentPlayer());
        remainingSeeds--;
      }
    }
  }
}
