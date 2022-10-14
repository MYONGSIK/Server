package com.example.myongsick.domain.food.controller;

import com.example.myongsick.domain.food.dto.response.WeekFoodResponse;
import com.example.myongsick.domain.food.service.FoodService;
import com.example.myongsick.global.object.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/foods")
public class FoodController {

//야믈 암호화
//
    private final FoodService foodService;

    /**
     * 음식 주단위 조회
     * @param toDay
     */
    @GetMapping("")
    public ApplicationResponse<WeekFoodResponse> getWeekFoods(String toDay){
        return ApplicationResponse.ok(foodService.getWeekFoods(toDay));
    }

}
