package com.github.juanmougan.mancala.exceptions;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = false)
public class IllegalMovementException extends RuntimeException {

  String message;
}
