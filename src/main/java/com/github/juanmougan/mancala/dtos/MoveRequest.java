package com.github.juanmougan.mancala.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MoveRequest {

  // TODO maybe PlayerType made more sense
  private PlayerDto player;
  private int pit;
}
