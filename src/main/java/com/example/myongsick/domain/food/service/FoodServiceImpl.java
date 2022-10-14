package com.example.myongsick.domain.food.service;

import com.example.myongsick.domain.food.dto.response.WeekFoodResponse;
import com.example.myongsick.domain.food.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodServiceImpl implements FoodService{

    private final FoodRepository foodRepository;

    @Override
    public WeekFoodResponse getWeekFoods(String toDay) {
        //조회 기준의 주 별 음식 보여주기
        return null;
    }


}
