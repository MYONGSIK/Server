package com.example.myongsick.domain.meal.exception.excute;

import static com.example.myongsick.domain.meal.exception.MealExceptionEnum.NOT_FOUND_WEEK;

import com.example.myongsick.domain.meal.exception.MealException;

public class NotFoundWeekException extends MealException {
    public NotFoundWeekException() {
        super(NOT_FOUND_WEEK.getCODE(), NOT_FOUND_WEEK.getHttpStatus(), NOT_FOUND_WEEK.getMESSAGE());
    }
}
