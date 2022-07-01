package com.github.juanmougan.mancala.utils;

import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.github.juanmougan.mancala.models.Board;
import com.github.juanmougan.mancala.models.Player;

public class BoardMocks {

  public static Board mockBoard(final Player south, final Player north) {
    final Board board = mock(Board.class);
    when(board.getSouth()).thenReturn(south);
    when(board.getNorth()).thenReturn(north);
    when(board.getCurrentPlayer()).thenReturn(south);
    doCallRealMethod().when(board).switchPlayer();
    return board;
  }
}
