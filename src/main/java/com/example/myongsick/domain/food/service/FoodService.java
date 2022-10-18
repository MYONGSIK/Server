package com.example.myongsick.domain.food.service;


import com.example.myongsick.domain.food.dto.request.DinnerAddRequest;
import com.example.myongsick.domain.food.dto.request.LunchAddRequest;
import com.example.myongsick.domain.food.dto.request.MindFoodRequest;
import com.example.myongsick.domain.food.dto.response.DaysFoodResponse;
import com.example.myongsick.domain.food.dto.response.WeekFoodResponse;

import java.util.List;

public interface FoodService {

    List<List<WeekFoodResponse>> getWeekFoods();

    Void addWeek(String startDay, String endDay);

    Void addDinner(DinnerAddRequest dinnerAddRequest);

    List<DaysFoodResponse> getDaysFoods();


    Void mindFood(MindFoodRequest mindFoodRequest);

    Void addLunch(LunchAddRequest lunchAddRequest);
}
