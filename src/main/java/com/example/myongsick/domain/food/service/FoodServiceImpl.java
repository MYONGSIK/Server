package com.example.myongsick.domain.food.service;

import com.example.myongsick.domain.food.dto.request.FoodAddRequest;
import com.example.myongsick.domain.food.dto.request.MindFoodRequest;
import com.example.myongsick.domain.food.dto.response.DaysFoodResponse;
import com.example.myongsick.domain.food.dto.response.WeekFoodResponse;
import com.example.myongsick.domain.food.entity.Food;
import com.example.myongsick.domain.food.entity.Week;
import com.example.myongsick.domain.food.exception.NotOperated;
import com.example.myongsick.domain.food.repository.FoodRepository;
import com.example.myongsick.domain.food.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodServiceImpl implements FoodService{

    private final FoodRepository foodRepository;
    private final WeekRepository weekRepository;

    @Override
    public List<WeekFoodResponse> getWeekFoods() {
        Week week = weekRepository.findByStartDayLessThanEqualAndEndDayGreaterThanEqual(LocalDate.now(), LocalDate.now()).get();
        return WeekFoodResponse.toEntity(foodRepository.findByWeekAndClassification(week, "중식"), foodRepository.findByWeekAndClassification(week, "석식"));
    }

    @Override
    @Transactional
    public Void addWeek(String startDay, String endDay) {
        weekRepository.save(Week.builder()
                .startDay(LocalDate.parse(startDay))
                .endDay(LocalDate.parse(endDay))
                .build());

        return null;
    }

    @Override
    @Transactional
    public Void addFoods(FoodAddRequest foodAddRequest) {
        foodRepository.save(
            Food.builder()
                    .toDay(foodAddRequest.getToDay())
                    .classification(foodAddRequest.getClassification())
                    .status(foodAddRequest.getStatus())
                    .food1(foodAddRequest.getFood1())
                    .food2(foodAddRequest.getFood2())
                    .food3(foodAddRequest.getFood3())
                    .food4(foodAddRequest.getFood4())
                    .food5(foodAddRequest.getFood5())
                    .food6(foodAddRequest.getFood6())
                    .week(weekRepository.findByStartDayLessThanEqualAndEndDayGreaterThanEqual(foodAddRequest.getToDay(), foodAddRequest.getToDay()).get())
                    .build()
        );
        return null;
    }

    @Override
    public List<DaysFoodResponse> getDaysFoods() {

        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        if(dayOfWeek.equals(DayOfWeek.SATURDAY)){
            throw new NotOperated();
        }else if(dayOfWeek.equals(DayOfWeek.SUNDAY)){
            throw new NotOperated();
        }

        return DaysFoodResponse.toEntity(foodRepository.findByToDay(LocalDate.now()));
    }

    @Override
    @Transactional
    public Void mindFood(MindFoodRequest mindFoodRequest) {
        Food food = foodRepository.findByToDayAndClassification(mindFoodRequest.getToDay(), mindFoodRequest.getClassification()).get();
        food.mindReflection(mindFoodRequest.getMind(),mindFoodRequest.getCalculation());
        return null;
    }



}
