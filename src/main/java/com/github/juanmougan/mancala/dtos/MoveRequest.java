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

  PlayerType playerType;
  private int pit;
}
