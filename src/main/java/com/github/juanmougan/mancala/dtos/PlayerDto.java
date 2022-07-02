package com.github.juanmougan.mancala.dtos;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerDto {

  // TODO delete this, it's too much responsibility in the client side
  private UUID id;

  private String name;

  private PlayerType type;
}
