package com.example.myongsick.domain.food.dto.response;

import com.example.myongsick.domain.food.entity.Dinner;
import com.example.myongsick.domain.food.entity.Lunch;
import com.example.myongsick.global.util.DayOfTheWeek;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "주 단위 음식 조회")
public class WeekFoodResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate toDay;

    private String classification; //중식 석식
    private String status; //운영 미운영
    private String dayOfTheWeek;
    private String type;

    private List<String> foods;



    public static List<WeekFoodResponse> toEntity(List<Lunch> lunchesA, List<Lunch> lunchesB, List<Dinner> dinners) {
        List<WeekFoodResponse> weekFoodResponses = new ArrayList<>();
        for(int i=0; i< dinners.size(); i++){
            addList(lunchesA, weekFoodResponses, i);
            addList(lunchesB, weekFoodResponses, i);
            weekFoodResponses.add(
                    WeekFoodResponse.builder()
                            .toDay(dinners.get(i).getToDay())
                            .status(dinners.get(i).getStatus())
                            .classification("석식")
                            .type(null)
                            .dayOfTheWeek(DayOfTheWeek.DayOfTheWeekConvert(dinners.get(i).getToDay()))
                            .foods(Arrays.asList(dinners.get(i).getDinner1(), dinners.get(i).getDinner2(),dinners.get(i).getDinner3(),dinners.get(i).getDinner4(),dinners.get(i).getDinner5(),dinners.get(i).getDinner6()))
                            .build()
            );
        }
        return weekFoodResponses;
    }

    private static void addList(List<Lunch> lunches, List<WeekFoodResponse> weekFoodResponses, int i) {
        weekFoodResponses.add(
                WeekFoodResponse.builder()
                        .toDay(lunches.get(i).getToDay())
                        .status(lunches.get(i).getStatus())
                        .classification("중식")
                        .type(lunches.get(i).getType())
                        .dayOfTheWeek(DayOfTheWeek.DayOfTheWeekConvert(lunches.get(i).getToDay()))
                        .foods(Arrays.asList(lunches.get(i).getLunch1(), lunches.get(i).getLunch2(),lunches.get(i).getLunch3(),lunches.get(i).getLunch4(),lunches.get(i).getLunch5(),lunches.get(i).getLunch6()))
                        .build()
        );
    }

}
