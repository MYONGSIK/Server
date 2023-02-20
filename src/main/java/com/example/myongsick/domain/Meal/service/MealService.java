package com.example.myongsick.domain.v2.Meal.service;

import com.example.myongsick.domain.v2.Meal.dto.request.MealCreateReq;
import com.example.myongsick.domain.v2.Meal.dto.request.MealNotRegisterReq;
import com.example.myongsick.domain.v2.Meal.dto.response.MealResponse;

import java.util.List;

public interface MealService {
    List<MealResponse> getWeekFoods(String area);

    List<MealResponse> getDaysFoods(String area);

    Boolean createMeal(MealCreateReq mealCreateReq);

    Boolean createArea(String area);

    Boolean createWeek(String startDay, String endDay);

    Boolean notRegisterMeal(MealNotRegisterReq mealNotRegisterReq);

}
