package com.example.myongsick.domain.food.exception;

import com.example.myongsick.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public abstract class FoodException extends ApplicationException {
    protected FoodException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
