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

    private String status; //운영 미운영
    private String dayOfTheWeek;

    private List<String> lunchA;
    private List<String> lunchB;
    private List<String> dinner;



    public static List<WeekFoodResponse> toEntity(List<Lunch> lunchesA, List<Lunch> lunchesB, List<Dinner> dinners) {
        List<WeekFoodResponse> weekFoodResponses = new ArrayList<>();



        for(int i=0; i<5; i++) {//일자별
            weekFoodResponses.add(
                WeekFoodResponse.builder()
                        .toDay(lunchesA.get(i).getToDay())
                        .dayOfTheWeek(DayOfTheWeek.DayOfTheWeekConvert(dinners.get(i).getToDay()))
                        .status(lunchesA.get(i).getStatus())
                        .lunchA(Arrays.asList(lunchesA.get(i).getLunch1(),lunchesA.get(i).getLunch2(),lunchesA.get(i).getLunch3(),lunchesA.get(i).getLunch4(),lunchesA.get(i).getLunch5(),lunchesA.get(i).getLunch6()))
                        .lunchB(Arrays.asList(lunchesB.get(i).getLunch1(),lunchesB.get(i).getLunch2(),lunchesB.get(i).getLunch3(),lunchesB.get(i).getLunch4(),lunchesB.get(i).getLunch5(),lunchesB.get(i).getLunch6()))
                        .dinner(Arrays.asList(dinners.get(i).getDinner1(),dinners.get(i).getDinner2(),dinners.get(i).getDinner3(),lunchesB.get(i).getLunch4(),dinners.get(i).getDinner5(),dinners.get(i).getDinner6()))
                        .build()
            );
        }
        return weekFoodResponses;
    }

}
