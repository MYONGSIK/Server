package com.example.myongsick.domain.Meal.exception.excute;


import com.example.myongsick.domain.Meal.exception.MealException;

import static com.example.myongsick.domain.Meal.exception.MealExceptionEnum.NOT_FOUND_WEEK;

public class NotFoundWeekException extends MealException {
    public NotFoundWeekException() {
        super(NOT_FOUND_WEEK.getCODE(), NOT_FOUND_WEEK.getHttpStatus(), NOT_FOUND_WEEK.getMESSAGE());
    }
}
