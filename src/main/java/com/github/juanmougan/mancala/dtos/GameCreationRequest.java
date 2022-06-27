package com.github.juanmougan.mancala.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameCreationRequest {

  private String playerSouth;
  private String playerNorth;
}
