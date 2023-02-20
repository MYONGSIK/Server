package com.example.myongsick.domain.v2.Meal.exception.excute;

import com.example.myongsick.domain.v2.Meal.exception.MealException;
import org.springframework.http.HttpStatus;

import static com.example.myongsick.domain.v2.Meal.exception.MealExceptionEnum.ALREADY_AREA;

public class AlreadyAreaException extends MealException {
    public AlreadyAreaException() {
        super(ALREADY_AREA.getCODE(), ALREADY_AREA.getHttpStatus(), ALREADY_AREA.getMESSAGE());
    }
}
