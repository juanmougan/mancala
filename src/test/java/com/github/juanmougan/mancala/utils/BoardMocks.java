package com.github.juanmougan.mancala.utils;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.juanmougan.mancala.dtos.PlayerType;
import com.github.juanmougan.mancala.models.Board;
import com.github.juanmougan.mancala.models.Player;
import com.github.juanmougan.mancala.services.BoardService;

public class BoardMocks {

  public static Board mockBoard(final Player south, final Player north) {
    final Board board = mock(Board.class);
    when(board.getSouth()).thenReturn(south);
    when(board.getNorth()).thenReturn(north);
    when(board.getCurrentPlayer()).thenReturn(south);
    doCallRealMethod().when(board).switchPlayer();
    return board;
  }

  public static Board mockRealBoard() {
    final BoardService boardService = new BoardService();
    final Player south = PlayerMocks.mockPlayer(PlayerType.SOUTH);
    final Player north = PlayerMocks.mockPlayer(PlayerType.NORTH);
    return boardService.createInitialBoard(south, north);
  }
}
