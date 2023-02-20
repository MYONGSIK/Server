package com.example.myongsick.domain.v2.Meal.exception.excute;

import com.example.myongsick.domain.food.exception.FoodException;
import com.example.myongsick.domain.v2.Meal.exception.MealException;
import com.example.myongsick.domain.v2.Meal.service.MealService;
import org.springframework.http.HttpStatus;

import static com.example.myongsick.domain.food.exception.FoodExceptionEnum.*;

public class NotOperatedException extends MealException {
    public NotOperatedException() {
        super(NOT_OPERATED.CODE,
                NOT_OPERATED.httpStatus,
                NOT_OPERATED.MESSAGE);
    }
}
