package com.example.myongsick.domain.review.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewExceptionEnum {

  REVIEW_NOT_FOUND("R0000", HttpStatus.NOT_FOUND, "해당하는 reviewId를 갖는 리뷰가 존재하지 않습니다."),
  ;

  public final String CODE;
  public final HttpStatus httpStatus;
  public final String MESSAGE;
}
