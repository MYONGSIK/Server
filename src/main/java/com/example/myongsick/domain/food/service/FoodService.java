package com.example.myongsick.domain.food.service;


import com.example.myongsick.domain.food.dto.request.FoodAddRequest;
import com.example.myongsick.domain.food.dto.response.DaysFoodResponse;
import com.example.myongsick.domain.food.dto.response.WeekFoodResponse;

import java.util.List;

public interface FoodService {

    List<DaysFoodResponse> getWeekFoods();

    Void addWeek(String startDay, String endDay);

    Void addFoods(FoodAddRequest foodAddRequest);

    List<DaysFoodResponse> getDaysFoods();
}
