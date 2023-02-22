package com.example.myongsick.domain.scrap.exception;

import com.example.myongsick.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class ScrapException extends ApplicationException {

  protected ScrapException(String errorCode, HttpStatus httpStatus,
      String message) {
    super(errorCode, httpStatus, message);
  }
}
