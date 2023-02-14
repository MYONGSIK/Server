package com.example.myongsick.domain.food.controller;

import com.example.myongsick.domain.food.dto.request.DinnerAddRequest;
import com.example.myongsick.domain.food.dto.request.LunchAddRequest;
import com.example.myongsick.domain.food.dto.request.MindFoodRequest;
import com.example.myongsick.domain.food.dto.response.DaysFoodResponse;
import com.example.myongsick.domain.food.dto.response.WeekFoodResponse;
import com.example.myongsick.domain.food.service.FoodService;
import com.example.myongsick.global.object.ApplicationResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/foods")
public class FoodController {

private final FoodService foodService;

    /**
     * 음식 주단위 조회
     */
    @GetMapping("/week")
    @ApiOperation(value = "음식 주 단위 조회")
    public ApplicationResponse<List<WeekFoodResponse>> getWeekFoods(){
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
     * 석식 메뉴 추가
     */
    @PostMapping("/dinner")
    public ApplicationResponse<Void> addDinner(@RequestBody DinnerAddRequest dinnerAddRequest){
        return ApplicationResponse.ok(foodService.addDinner(dinnerAddRequest));
    }

    /**
     * 중식 메뉴 추가
     */
    @PostMapping("/lunch")
    public ApplicationResponse<Void> addLunch(@RequestBody LunchAddRequest lunchAddRequest){
        return ApplicationResponse.ok(foodService.addLunch(lunchAddRequest));
    }

    /**
     * 음식에 대해서 좋음 싫음을 표시
     */
    @PostMapping("/love")
    @ApiOperation(value = "음식에 대해서 좋음, 싫음을 표시합니다.", notes = "중식의 경우, type = A or B \n mind = 'love', 'hate'를 표시 \n calculation은 'plus','minus'")
    public ApplicationResponse<Void> mindFood(@RequestBody MindFoodRequest mindFoodRequest){
        return ApplicationResponse.ok(foodService.mindFood(mindFoodRequest));
    }
}
