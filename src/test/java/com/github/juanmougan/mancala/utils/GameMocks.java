package com.github.juanmougan.mancala.utils;

import static com.github.juanmougan.mancala.dtos.PlayerType.NORTH;
import static com.github.juanmougan.mancala.dtos.PlayerType.SOUTH;
import static com.github.juanmougan.mancala.utils.PlayerMocks.mockPlayer;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.github.juanmougan.mancala.dtos.Board;
import com.github.juanmougan.mancala.dtos.MoveRequest;
import com.github.juanmougan.mancala.dtos.Player;
import com.github.juanmougan.mancala.dtos.PlayerType;
import com.github.juanmougan.mancala.models.Game;
import java.util.UUID;
import lombok.experimental.UtilityClass;
import org.mockito.Mockito;

@UtilityClass
public class GameMocks {

  public static MoveRequest getValidSouthMove(final int pit) {
    return getValidSouthMove(pit, SOUTH);
  }

  public static MoveRequest getValidSouthMove(final int pit, final PlayerType playerType) {
    return MoveRequest.builder()
        .playerType(SOUTH)
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
    Mockito.doCallRealMethod().when(game).performMove();
    return game;
  }

  public static Game mockRealGame() {
    final Player south = Player.builder()
        .type(SOUTH)
        .name("south")
        .build();
    final Player north = Player.builder()
        .type(NORTH)
        .name("north")
        .build();
    return Game.builder()
        .board(Board.builder()
            .south(south)
            .north(north)
            .currentPlayer(south)
            .build())
        .build();
  }
}
