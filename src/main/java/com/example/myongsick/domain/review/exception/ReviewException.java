package com.example.myongsick.domain.review.exception;

import com.example.myongsick.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class ReviewException extends ApplicationException {

  protected ReviewException(String errorCode, HttpStatus httpStatus,
      String message) {
    super(errorCode, httpStatus, message);
  }
}
