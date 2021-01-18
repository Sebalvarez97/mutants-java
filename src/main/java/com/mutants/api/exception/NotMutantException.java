package com.mutants.api.exception;

import lombok.Data;

@Data
public class NotMutantException extends RuntimeException {
  public NotMutantException() {}

  public NotMutantException(String message) {
    super(message);
  }

  public NotMutantException(String message, Throwable cause) {
    super(message, cause);
  }

  public NotMutantException(Throwable cause) {
    super(cause);
  }

  public NotMutantException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
