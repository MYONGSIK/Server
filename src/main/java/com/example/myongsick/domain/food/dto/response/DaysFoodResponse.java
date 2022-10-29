package com.example.myongsick.domain.food.dto.response;

import com.example.myongsick.domain.food.entity.Dinner;
import com.example.myongsick.domain.food.entity.Lunch;
import com.example.myongsick.global.util.DayOfTheWeek;
import io.swagger.annotations.ApiModel;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "일 단위 음식 조회")
public class DaysFoodResponse {
    private LocalDate toDay;
    private String dayOfTheWeek;

    private String classification; //중식 석식
    private String type;
    private String status; //운영 미운영
    private String food1;
    private String food2;
    private String food3;
    private String food4;
    private String food5;
    private String food6;

    public static List<DaysFoodResponse> toEntity(List<Lunch> lunches, Dinner dinner) {
        List<DaysFoodResponse> daysFoodResponseList = new ArrayList<>();

        for (Lunch lunch : lunches) {
            daysFoodResponseList.add(
                    DaysFoodResponse.builder()
                            .toDay(lunch.getToDay())
                            .classification("중식")
                            .dayOfTheWeek(DayOfTheWeek.DayOfTheWeekConvert(lunch.getToDay()))
                            .type(lunch.getType())
                            .status(lunch.getStatus())
                            .food1(lunch.getLunch1())
                            .food2(lunch.getLunch2())
                            .food3(lunch.getLunch3())
                            .food4(lunch.getLunch4())
                            .food5(lunch.getLunch5())
                            .food6(lunch.getLunch6())
                            .build()
            );
        }
        daysFoodResponseList.add(
                DaysFoodResponse.builder()
                        .toDay(dinner.getToDay())
                        .classification("석식")
                        .dayOfTheWeek(DayOfTheWeek.DayOfTheWeekConvert(dinner.getToDay()))
                        .type(null)
                        .status(dinner.getStatus())
                        .food1(dinner.getDinner1())
                        .food2(dinner.getDinner2())
                        .food3(dinner.getDinner3())
                        .food4(dinner.getDinner4())
                        .food5(dinner.getDinner5())
                        .food6(dinner.getDinner6())
                        .build()
        );
        return daysFoodResponseList;
    }
}
