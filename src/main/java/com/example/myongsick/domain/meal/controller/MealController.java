package com.example.myongsick.domain.meal.controller;


import com.example.myongsick.domain.meal.dto.request.MealCreateReq;
import com.example.myongsick.domain.meal.dto.request.MealEvaluateReq;
import com.example.myongsick.domain.meal.dto.request.MealNotRegisterReq;
import com.example.myongsick.domain.meal.dto.response.MealResponse;
import com.example.myongsick.domain.meal.service.MealService;
import com.example.myongsick.global.object.ApplicationResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v2/meals")
public class MealController {

    private final MealService mealService;

    /**
     * 음식 주단위 조회
     */
    @GetMapping("/week/{area}")
    @ApiOperation(value = "음식 주 단위 조회")
    public ApplicationResponse<List<MealResponse>> getWeekFoods(@PathVariable String area){
        return ApplicationResponse.ok(mealService.getWeekFoods(area));
    }

    /**
     * 음식 일단위 조회
     */
    @GetMapping("/{area}")
    @ApiOperation(value = "음식 일 단위 조회")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "(F0000) \n 공휴일은 식당을 운영하지 않습니다.")
    })
    public ApplicationResponse<List<MealResponse>> getDaysFoods(@PathVariable(value = "area") String area){
        return ApplicationResponse.ok(mealService.getDaysFoods(area));
    }


    @PostMapping("")
    @ApiOperation(value = "음식 추가")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "(F0000) \n 공휴일은 식당을 운영하지 않습니다.")
    })
    public ApplicationResponse<Boolean> createMeal(@RequestBody @Valid MealCreateReq mealCreateReq){
        return ApplicationResponse.ok(mealService.createMeal(mealCreateReq));
    }

    @PostMapping("/area")
    @ApiOperation(value = "지역 추가")
    public ApplicationResponse<Boolean> createArea(String area){
        return ApplicationResponse.ok(mealService.createArea(area));
    }

    @PostMapping("/week")
    @ApiOperation(value = "주 단위 추가")
    public ApplicationResponse<Boolean> createWeek(String startDay, String endDay){
        return ApplicationResponse.ok(mealService.createWeek(startDay,endDay));
    }

    @PostMapping("/once")
    @ApiOperation(value = "주 단위 '등록된 식당 내용이 없습니다.' 등록하기")
    public ApplicationResponse<Boolean> notRegisterMeal(@RequestBody MealNotRegisterReq mealNotRegisterReq){
        return ApplicationResponse.ok(mealService.notRegisterMeal(mealNotRegisterReq));
    }

    @PostMapping("/evaluate")
    @ApiOperation(value = "해당 식단에 대해서 좋아요/싫어요를 남깁니다.")
    public ApplicationResponse<Boolean> evaluate(@RequestBody @Valid MealEvaluateReq mealEvaluateReq){
        return ApplicationResponse.ok(mealService.evaluate(mealEvaluateReq));
    }

}
