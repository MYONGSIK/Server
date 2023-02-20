package com.example.myongsick.domain.v2.Meal.exception.excute;

import com.example.myongsick.domain.v2.Meal.exception.MealException;
import org.springframework.http.HttpStatus;

import static com.example.myongsick.domain.v2.Meal.exception.MealExceptionEnum.NOT_FOUND_AREA;


public class NotFoundAreaException extends MealException {
    public NotFoundAreaException() {
        super(NOT_FOUND_AREA.getCODE(), NOT_FOUND_AREA.getHttpStatus(), NOT_FOUND_AREA.getMESSAGE());
    }
}
