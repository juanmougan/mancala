package com.github.juanmougan.mancala.dtos;

import com.github.juanmougan.mancala.models.Board;
import com.github.juanmougan.mancala.models.Player;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameResponse {

  private UUID id;
  private Status status;
  private Player next;
  private Board board;
}
