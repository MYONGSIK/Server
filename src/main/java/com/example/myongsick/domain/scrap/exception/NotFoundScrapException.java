package com.example.myongsick.domain.scrap.exception;

import static com.example.myongsick.domain.scrap.exception.ScrapExceptionEnum.NOT_FOUND_SCRAP;

public class NotFoundScrapException extends ScrapException{

  public NotFoundScrapException() {
    super(NOT_FOUND_SCRAP.CODE, NOT_FOUND_SCRAP.httpStatus, NOT_FOUND_SCRAP.MESSAGE);
  }
}
