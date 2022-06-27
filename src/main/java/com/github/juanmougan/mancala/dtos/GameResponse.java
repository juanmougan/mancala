package com.github.juanmougan.mancala.dtos;

import java.util.UUID;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameResponse {

  private UUID id;
}
