package com.example.myongsick.domain.meal.service;


import com.example.myongsick.domain.meal.dto.request.MealCreateReq;
import com.example.myongsick.domain.meal.dto.request.MealNotRegisterReq;
import com.example.myongsick.domain.meal.dto.response.MealResponse;

import java.util.List;

public interface MealService {
    List<MealResponse> getWeekFoods(String area);

    List<MealResponse> getDaysFoods(String area);

    Boolean createMeal(MealCreateReq mealCreateReq);

    Boolean createArea(String area);

    Boolean createWeek(String startDay, String endDay);

    Boolean notRegisterMeal(MealNotRegisterReq mealNotRegisterReq);

}
