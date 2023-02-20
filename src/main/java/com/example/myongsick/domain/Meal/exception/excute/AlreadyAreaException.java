package com.example.myongsick.domain.meal.exception.excute;


import com.example.myongsick.domain.meal.exception.MealException;

import static com.example.myongsick.domain.meal.exception.MealExceptionEnum.ALREADY_AREA;

public class AlreadyAreaException extends MealException {
    public AlreadyAreaException() {
        super(ALREADY_AREA.getCODE(), ALREADY_AREA.getHttpStatus(), ALREADY_AREA.getMESSAGE());
    }
}
