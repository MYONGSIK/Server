package com.example.myongsick.domain.user.exception;

import static com.example.myongsick.domain.user.exception.UserExceptionEnum.ALREADY_USER;

public class AlreadyUserException extends UserException{

  public AlreadyUserException() {
    super(ALREADY_USER.CODE, ALREADY_USER.httpStatus, ALREADY_USER.MESSAGE);
  }
}
