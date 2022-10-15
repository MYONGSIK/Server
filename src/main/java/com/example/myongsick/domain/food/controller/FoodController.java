package com.example.myongsick.domain.food.controller;

import com.example.myongsick.domain.food.dto.request.FoodAddRequest;
import com.example.myongsick.domain.food.dto.response.DaysFoodResponse;
import com.example.myongsick.domain.food.dto.response.WeekFoodResponse;
import com.example.myongsick.domain.food.service.FoodService;
import com.example.myongsick.global.object.ApplicationResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/foods")
public class FoodController {

private final FoodService foodService;

    /**
     * 음식 주단위 조회
     */
    @GetMapping("/week")
    @ApiOperation(value = "음식 주 단위 조회")
    public ApplicationResponse<List<DaysFoodResponse>> getWeekFoods(){
        return ApplicationResponse.ok(foodService.getWeekFoods());
    }

    /**
     * 음식 일단위 조회
     */
    @GetMapping("")
    @ApiOperation(value = "음식 일 단위 조회")
    @ApiResponses(value = {
         @ApiResponse(code = 400, message = "(F0000) \n 공휴일은 식당을 운영하지 않습니다.")
    })
    public ApplicationResponse<List<DaysFoodResponse>> getDaysFoods(){
        return ApplicationResponse.ok(foodService.getDaysFoods());
    }

    /**
     * 주 단위 추가
     */
    @PostMapping("/week")
    public ApplicationResponse<Void> addWeek(String startDay, String endDay){
        return ApplicationResponse.ok(foodService.addWeek(startDay,endDay));
    }

    /**
     * 음식 추가
     */
    @PostMapping("")
    public ApplicationResponse<Void> addFoods(@RequestBody FoodAddRequest foodAddRequest){
        return ApplicationResponse.ok(foodService.addFoods(foodAddRequest));
    }

}
