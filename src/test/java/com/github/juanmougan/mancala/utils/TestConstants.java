package com.github.juanmougan.mancala.utils;

import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TestConstants {

  public static final String STARTED_GAME_ID = "00000000-0000-0000-0000-000000000001";
  public static final UUID STARTED_GAME_UUID = UUID.fromString(TestConstants.STARTED_GAME_ID);
}
