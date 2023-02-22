package com.example.myongsick.domain.user.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum UserExceptionEnum {
  ALREADY_USER("U0000", HttpStatus.BAD_REQUEST, "이미 등록된 유저입니다."),
  NOT_FOUND_USER("U0001", HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다."),
  ;

  public final String CODE;
  public final HttpStatus httpStatus;
  public final String MESSAGE;
}
