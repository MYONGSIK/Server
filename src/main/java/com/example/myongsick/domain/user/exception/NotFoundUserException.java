package com.example.myongsick.domain.user.exception;

import static com.example.myongsick.domain.user.exception.UserExceptionEnum.NOT_FOUND_USER;

public class NotFoundUserException extends UserException{

  public NotFoundUserException() {
    super(NOT_FOUND_USER.CODE, NOT_FOUND_USER.httpStatus, NOT_FOUND_USER.MESSAGE);
  }
}
