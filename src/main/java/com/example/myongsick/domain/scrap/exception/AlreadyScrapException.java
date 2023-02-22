package com.example.myongsick.domain.scrap.exception;

import static com.example.myongsick.domain.scrap.exception.ScrapExceptionEnum.ALREADY_SCRAP_STORE;

public class AlreadyScrapException extends ScrapException{

  public AlreadyScrapException() {
    super(ALREADY_SCRAP_STORE.CODE, ALREADY_SCRAP_STORE.httpStatus, ALREADY_SCRAP_STORE.MESSAGE);
  }
}
