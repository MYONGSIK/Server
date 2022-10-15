package com.example.myongsick.domain.food.exception;

import org.springframework.http.HttpStatus;

import static com.example.myongsick.domain.food.exception.FoodExceptionEnum.*;

public class NotOperated extends FoodException{
    public NotOperated() {
        super(NOT_OPERATED.CODE,
                NOT_OPERATED.httpStatus,
                NOT_OPERATED.MESSAGE);
    }
}
