package com.example.myongsick.domain.meal.exception.excute;

import com.example.myongsick.domain.meal.exception.MealException;
import org.springframework.http.HttpStatus;

import static com.example.myongsick.domain.meal.exception.MealExceptionEnum.NOT_FOUND_MEAL;

public class NotfoundMealException extends MealException {
    public NotfoundMealException() {
        super(NOT_FOUND_MEAL.getCODE(), NOT_FOUND_MEAL.getHttpStatus(), NOT_FOUND_MEAL.getMESSAGE());
    }
}
