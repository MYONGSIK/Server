package com.example.myongsick.domain.v2.Meal.exception;

import com.example.myongsick.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public class MealException extends ApplicationException {
    protected MealException(String errorCode, HttpStatus httpStatus, String message) {
        super(errorCode, httpStatus, message);
    }
}
