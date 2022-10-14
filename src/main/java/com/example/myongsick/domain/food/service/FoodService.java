package com.example.myongsick.domain.food.service;


import com.example.myongsick.domain.food.dto.response.WeekFoodResponse;

public interface FoodService {

    WeekFoodResponse getWeekFoods(String toDay);

}
