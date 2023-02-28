package com.example.myongsick.domain.meal.service;

import com.example.myongsick.domain.meal.dto.request.MealEvaluateReq;
import com.example.myongsick.domain.meal.entity.*;
import com.example.myongsick.domain.meal.exception.excute.*;
import com.example.myongsick.domain.meal.repository.AreaRepository;
import com.example.myongsick.domain.food.entity.Week;
import com.example.myongsick.domain.food.repository.WeekRepository;
import com.example.myongsick.domain.meal.dto.request.MealCreateReq;
import com.example.myongsick.domain.meal.dto.request.MealNotRegisterReq;
import com.example.myongsick.domain.meal.dto.response.MealResponse;
import com.example.myongsick.domain.meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final WeekRepository weekRepository;
    private final AreaRepository areaRepository;

    @Override
    public List<MealResponse> getWeekFoods(String area) {
        Week week = weekRepository.findByStartDayLessThanEqualAndEndDayGreaterThanEqual(LocalDate.now(), LocalDate.now()).get();
        return MealResponse.toEntity(mealRepository.findByWeekAndArea(week, areaRepository.findByName(area).get()));
    }

    @Override
    public List<MealResponse> getDaysFoods(String area) {
        DayOfWeek dayOfWeek = LocalDate.now().getDayOfWeek();
        if(dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)){
            throw new NotOperatedException();
        }
        return MealResponse.toEntity(mealRepository.findByOfferedAtAndArea(LocalDate.now(),areaRepository.findByName(area).get()));
    }

    @Override
    @Transactional
    public Boolean createMeal(MealCreateReq mealCreateReq) {
        mealRepository.save(Meal.builder()
                .mealType(MealType.valueOf(mealCreateReq.getType()))
                .area(areaRepository.findByName(mealCreateReq.getArea()).orElseThrow(NotFoundAreaException::new))
                .week(weekRepository.findByStartDayLessThanEqualAndEndDayGreaterThanEqual(mealCreateReq.getOfferedAt(), mealCreateReq.getOfferedAt()).orElseThrow(
                    NotFoundWeekException::new))
                .offeredAt(mealCreateReq.getOfferedAt())
                .statusType(StatusType.valueOf(mealCreateReq.getStatus()))
                .menus(mealCreateReq.getMeals())
                .build());
        return true;
    }

    @Override
    @Transactional
    public Boolean createArea(String area) {
        if (areaRepository.findByName(area).isPresent()) throw new AlreadyAreaException();
        areaRepository.save(Area.builder().name(area).build());
        return true;
    }

    @Override
    @Transactional
    public Boolean createWeek(String startDay, String endDay) {
        weekRepository.save(Week.builder()
                .startDay(LocalDate.parse(startDay))
                .endDay(LocalDate.parse(endDay))
                .build());
        return true;
    }

    @Override
    @Transactional
    public Boolean notRegisterMeal(MealNotRegisterReq mealNotRegisterReq) {
        Area area = areaRepository.findByName(mealNotRegisterReq.getArea()).orElseThrow(NotFoundAreaException::new);
        List<Meal> meals = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            Week week = weekRepository.findByStartDayLessThanEqualAndEndDayGreaterThanEqual(mealNotRegisterReq.getStartedAt().plusDays(i), mealNotRegisterReq.getStartedAt().plusDays(i)).orElseThrow(NotFoundAreaException::new);
            if (area.getName().equals("MCC식당")){
                for(int j = 0; j < MealType.values().length; j++){
                    meals.add(
                            Meal.builder()
                                    .area(area)
                                    .week(week)
                                    .mealType(MealType.values()[j])
                                    .menus(List.of("","등록된 식단내용이(가) 없습니다.","","","",""))
                                    .offeredAt(mealNotRegisterReq.getStartedAt().plusDays(i))
                                    .statusType(StatusType.OPEN)
                                    .build()
                    );
                }
            }else{
                for(int j = 0; j < MealType.values().length-1; j++){
                    meals.add(
                            Meal.builder()
                                    .area(area)
                                    .week(week)
                                    .mealType(MealType.values()[j])
                                    .menus(List.of("","등록된 식단내용이(가) 없습니다.","","","",""))
                                    .offeredAt(mealNotRegisterReq.getStartedAt().plusDays(i))
                                    .statusType(StatusType.OPEN)
                                    .build()
                    );
                }
            }
        }
        mealRepository.saveAll(meals);
        return true;
    }

    @Override
    @Transactional
    public Boolean evaluate(MealEvaluateReq mealEvaluateReq) {
        Meal meal = mealRepository.findById(mealEvaluateReq.getMealId()).orElseThrow(NotfoundMealException::new);
        if (mealEvaluateReq.getMealEvaluate().equals(MealEvaluate.LOVE)){
            if (mealEvaluateReq.getCalculation().equals("plus")) meal.addLove();
            if (mealEvaluateReq.getCalculation().equals("minus")) meal.reduceLove();
        }
        if (mealEvaluateReq.getMealEvaluate().equals(MealEvaluate.HATE)){
            if (mealEvaluateReq.getCalculation().equals("plus")) meal.addHate();
            if (mealEvaluateReq.getCalculation().equals("minus")) meal.reduceHate();
        }
        return true;
    }

    @Override
    public List<List<MealResponse>> getWeekMealAndroid(String area) {
        Week week = weekRepository.findByStartDayLessThanEqualAndEndDayGreaterThanEqual(LocalDate.now(), LocalDate.now()).get();
        Area areaEntity = areaRepository.findByName(area).get();
        List<List<MealResponse>> mealResponses = new ArrayList<>();
        //하루씩 불러오기
        for(int i = 0; i < 5; i++){
            LocalDate localDate = week.getStartDay().plusDays(i);
            List<Meal> byAreaAndWeek = mealRepository.findByAreaAndAndOfferedAt(areaEntity, localDate);
            for (int j = 0; j < byAreaAndWeek.size(); j++){
                mealResponses.add((MealResponse.toEntity(byAreaAndWeek)));
            }
        }
        return mealResponses;
    }
}
