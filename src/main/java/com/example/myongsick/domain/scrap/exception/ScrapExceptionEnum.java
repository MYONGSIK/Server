package com.example.myongsick.domain.scrap.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ScrapExceptionEnum {

  ALREADY_SCRAP_STORE("S0000", HttpStatus.BAD_REQUEST, "이미 찜꽁한 가게입니다."),
  NOT_FOUND_SCRAP("S0001", HttpStatus.NOT_FOUND, "찜꽁 데이터가 존재하지 않습니다."),
  NOT_FOUND_STORE("S0002", HttpStatus.NOT_FOUND, "가게 데이터가 존재하지 않습니다."),
  ;

  public final String CODE;
  public final HttpStatus httpStatus;
  public final String MESSAGE;
}
