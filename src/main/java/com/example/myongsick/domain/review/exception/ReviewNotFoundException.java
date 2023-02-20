package com.example.myongsick.domain.review.exception;

import static com.example.myongsick.domain.review.exception.ReviewExceptionEnum.REVIEW_NOT_FOUND;

public class ReviewNotFoundException extends ReviewException{

  public ReviewNotFoundException() {
    super(REVIEW_NOT_FOUND.CODE, REVIEW_NOT_FOUND.httpStatus, REVIEW_NOT_FOUND.MESSAGE);
  }
}
