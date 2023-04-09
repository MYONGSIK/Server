package com.example.myongsick.domain.scrap.exception;


import static com.example.myongsick.domain.scrap.exception.ScrapExceptionEnum.NOT_FOUND_STORE;

public class NotFoundStoreException extends ScrapException{

  public NotFoundStoreException() {
    super(NOT_FOUND_STORE.CODE, NOT_FOUND_STORE.httpStatus, NOT_FOUND_STORE.MESSAGE);
  }
}
