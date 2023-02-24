package com.example.myongsick.domain.meal.exception.excute;


import com.example.myongsick.domain.meal.exception.MealException;

import static com.example.myongsick.domain.meal.exception.MealExceptionEnum.NOT_FOUND_AREA;

public class NotFoundAreaException extends MealException {
    public NotFoundAreaException() {
        super(NOT_FOUND_AREA.getCODE(), NOT_FOUND_AREA.getHttpStatus(), NOT_FOUND_AREA.getMESSAGE());
    }
}
