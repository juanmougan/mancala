package com.github.juanmougan.mancala.utils;

import static com.github.juanmougan.mancala.dtos.PlayerType.NORTH;
import static com.github.juanmougan.mancala.dtos.PlayerType.SOUTH;
import static com.github.juanmougan.mancala.utils.PlayerMocks.mockNorthPlayer;
import static com.github.juanmougan.mancala.utils.PlayerMocks.mockPlayer;
import static com.github.juanmougan.mancala.utils.PlayerMocks.mockSouthPlayer;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.github.juanmougan.mancala.dtos.MoveRequest;
import com.github.juanmougan.mancala.dtos.PlayerDto;
import com.github.juanmougan.mancala.models.Board;
import com.github.juanmougan.mancala.models.Game;
import com.github.juanmougan.mancala.models.Player;
import com.github.juanmougan.mancala.services.BoardService;
import java.util.UUID;
import lombok.experimental.UtilityClass;
import org.mockito.Mockito;

@UtilityClass
public class GameMocks {

  public static MoveRequest getValidSouthMove(final int pit) {
    final PlayerDto south = mockSouthPlayer();
    return getValidMove(pit, south);
  }

  public static MoveRequest getValidNorthMove(final int pit) {
    final PlayerDto north = mockNorthPlayer();
    return getValidMove(pit, north);
  }

  public static MoveRequest getValidMove(final int pit, final PlayerDto player) {
    return MoveRequest.builder()
        .player(player)
        .pit(pit)
        .build();
  }

  public static Game mockGame() {
    final Game game = spy(Game.class);
    when(game.getId()).thenReturn(UUID.randomUUID());
    final Player south = mockPlayer(SOUTH);
    final Player north = mockPlayer(NORTH);
    final Board board = BoardMocks.mockBoard(south, north);
    when(game.getBoard()).thenReturn(board);
    Mockito.doCallRealMethod().when(game).switchCurrentPlayer();
    return game;
  }

  public static Game mockRealGame() {
    final BoardService boardService = new BoardService();
    final Player south = Player.builder()
        .type(SOUTH)
        .name("south")
        .build();
    final Player north = Player.builder()
        .type(NORTH)
        .name("north")
        .build();
    return Game.builder()
        .board(boardService.createInitialBoard(south, north))
        .build();
  }
}
