package com.example.myongsick.domain.Meal.service;

import com.example.myongsick.domain.Meal.repository.AreaRepository;
import com.example.myongsick.domain.food.entity.Week;
import com.example.myongsick.domain.food.repository.WeekRepository;
import com.example.myongsick.domain.Meal.entity.Meal;
import com.example.myongsick.domain.Meal.dto.request.MealCreateReq;
import com.example.myongsick.domain.Meal.dto.request.MealNotRegisterReq;
import com.example.myongsick.domain.Meal.dto.response.MealResponse;
import com.example.myongsick.domain.Meal.entity.Area;
import com.example.myongsick.domain.Meal.entity.MealType;
import com.example.myongsick.domain.Meal.entity.StatusType;
import com.example.myongsick.domain.Meal.exception.excute.AlreadyAreaException;
import com.example.myongsick.domain.Meal.exception.excute.NotFoundAreaException;
import com.example.myongsick.domain.Meal.exception.excute.NotOperatedException;
import com.example.myongsick.domain.Meal.repository.MealRepository;
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
                .week(weekRepository.findByStartDayLessThanEqualAndEndDayGreaterThanEqual(mealCreateReq.getOfferedAt(), mealCreateReq.getOfferedAt()).orElseThrow(NotFoundAreaException::new))
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
        return null;
    }

}
