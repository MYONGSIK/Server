package com.example.myongsick.domain.Meal.exception.excute;

import com.example.myongsick.domain.Meal.exception.MealException;

import static com.example.myongsick.domain.food.exception.FoodExceptionEnum.NOT_OPERATED;

public class NotOperatedException extends MealException {
    public NotOperatedException() {
        super(NOT_OPERATED.CODE,
                NOT_OPERATED.httpStatus,
                NOT_OPERATED.MESSAGE);
    }
}
