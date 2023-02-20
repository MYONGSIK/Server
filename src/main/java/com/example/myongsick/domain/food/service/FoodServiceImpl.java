package com.example.myongsick.domain.food.service;

import com.example.myongsick.domain.Meal.exception.excute.NotOperatedException;
import com.example.myongsick.domain.food.dto.request.DinnerAddRequest;
import com.example.myongsick.domain.food.dto.request.LunchAddRequest;
import com.example.myongsick.domain.food.dto.request.MindFoodRequest;
import com.example.myongsick.domain.food.dto.response.DaysFoodResponse;
import com.example.myongsick.domain.food.dto.response.WeekFoodResponse;
import com.example.myongsick.domain.food.entity.Dinner;
import com.example.myongsick.domain.food.entity.Lunch;
import com.example.myongsick.domain.food.entity.Week;
import com.example.myongsick.domain.food.repository.DinnerRepository;
import com.example.myongsick.domain.food.repository.LunchRepository;
import com.example.myongsick.domain.food.repository.WeekRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodServiceImpl implements FoodService{

    private final WeekRepository weekRepository;
    private final DinnerRepository dinnerRepository;
    private final LunchRepository lunchRepository;

    @Override
    public List<WeekFoodResponse> getWeekFoods() {
        Week week = weekRepository.findByStartDayLessThanEqualAndEndDayGreaterThanEqual(LocalDate.now(), LocalDate.now()).get();
        return WeekFoodResponse.toEntity(lunchRepository.findByWeekAndType(week,"A"), lunchRepository.findByWeekAndType(week,"B"),dinnerRepository.findByWeek(week));
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
    public Void addDinner(DinnerAddRequest dinnerAddRequest) {
        dinnerRepository.save(
                Dinner.builder()
                        .toDay(dinnerAddRequest.getToDay())
                        .status(dinnerAddRequest.getStatus())
                        .dinner1(dinnerAddRequest.getDinner1())
                        .dinner2(dinnerAddRequest.getDinner2())
                        .dinner3(dinnerAddRequest.getDinner3())
                        .dinner4(dinnerAddRequest.getDinner4())
                        .dinner5(dinnerAddRequest.getDinner5())
                        .dinner6(dinnerAddRequest.getDinner6())
                        .week(weekRepository.findByStartDayLessThanEqualAndEndDayGreaterThanEqual(dinnerAddRequest.getToDay(), dinnerAddRequest.getToDay()).get())
                        .build()
        );
        return null;
    }

    @Override
    public List<DaysFoodResponse> getDaysFoods() {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        if(dayOfWeek.equals(DayOfWeek.SATURDAY)){
            throw new NotOperatedException();
        }else if(dayOfWeek.equals(DayOfWeek.SUNDAY)){
            throw new NotOperatedException();
        }

        return DaysFoodResponse.toEntity(lunchRepository.findByToDay(LocalDate.now()), dinnerRepository.findByToDay(LocalDate.now()).get());
    }

    @Override
    @Transactional
    public Void mindFood(MindFoodRequest mindFoodRequest) {
        Long value = 0L;
        if(mindFoodRequest.getClassification().equals("중식")){//중식
            Lunch lunch = lunchRepository.findByToDayAndType(mindFoodRequest.getToDay(), mindFoodRequest.getType()).get();
            lunch.mindReflection(mindFoodRequest.getMind(),mindFoodRequest.getCalculation());
        }else{//석식
            Dinner dinner = dinnerRepository.findByToDay(mindFoodRequest.getToDay()).get();
            dinner.mindReflection(mindFoodRequest.getMind(),mindFoodRequest.getCalculation());
        }

        return null;
    }

    @Override
    @Transactional
    public Void addLunch(LunchAddRequest lunchAddRequest) {
        lunchRepository.save(
                Lunch.builder()
                        .type(lunchAddRequest.getType())
                        .toDay(lunchAddRequest.getToDay())
                        .status(lunchAddRequest.getStatus())
                        .lunch1(lunchAddRequest.getLunch1())
                        .lunch2(lunchAddRequest.getLunch2())
                        .lunch3(lunchAddRequest.getLunch3())
                        .lunch4(lunchAddRequest.getLunch4())
                        .lunch5(lunchAddRequest.getLunch5())
                        .lunch6(lunchAddRequest.getLunch6())
                        .week(weekRepository.findByStartDayLessThanEqualAndEndDayGreaterThanEqual(lunchAddRequest.getToDay(), lunchAddRequest.getToDay()).get())
                        .build()
        );
        return null;
    }
}
