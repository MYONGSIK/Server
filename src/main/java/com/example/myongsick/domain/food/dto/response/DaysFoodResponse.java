package com.example.myongsick.domain.food.dto.response;

import com.example.myongsick.domain.food.entity.Food;
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
    private String status; //운영 미운영
    private String food1;
    private String food2;
    private String food3;
    private String food4;
    private String food5;
    private String food6;

    public static List<DaysFoodResponse> toEntity(List<Food> foods){
        List<DaysFoodResponse> daysFoodResponseList = new ArrayList<>();

        for (Food food : foods) {
            daysFoodResponseList.add(
                DaysFoodResponse.builder()
                        .toDay(food.getToDay())
                        .dayOfTheWeek(DayOfTheWeek.DayOfTheWeekConvert(food.getToDay()))
                        .classification(food.getClassification())
                        .status(food.getStatus())
                        .food1(food.getFood1())
                        .food2(food.getFood2())
                        .food3(food.getFood3())
                        .food4(food.getFood4())
                        .food5(food.getFood5())
                        .food6(food.getFood6())
                        .build()
            );
        }
        return daysFoodResponseList;
    }
}
