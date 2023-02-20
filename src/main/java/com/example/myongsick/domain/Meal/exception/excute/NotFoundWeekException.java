package com.example.myongsick.domain.v2.Meal.exception.excute;

import com.example.myongsick.domain.v2.Meal.exception.MealException;
import org.springframework.http.HttpStatus;

import static com.example.myongsick.domain.v2.Meal.exception.MealExceptionEnum.NOT_FOUND_WEEK;

public class NotFoundWeekException extends MealException {
    public NotFoundWeekException() {
        super(NOT_FOUND_WEEK.getCODE(), NOT_FOUND_WEEK.getHttpStatus(), NOT_FOUND_WEEK.getMESSAGE());
    }
}
