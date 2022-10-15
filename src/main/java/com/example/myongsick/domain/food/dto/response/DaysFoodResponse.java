package com.example.myongsick.domain.food.dto.response;

import com.example.myongsick.domain.food.entity.Food;
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
@ApiModel(description = "주 단위 음식 조회")
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
            String dayOf = "";
            switch (food.getToDay().getDayOfWeek()){
                case SUNDAY:
                    dayOf="일요일";
                    break;
                case MONDAY:
                    dayOf="월요일";
                    break;
                case TUESDAY:
                    dayOf="화요일";
                    break;
                case WEDNESDAY:
                    dayOf="수요일";
                    break;
                case THURSDAY:
                    dayOf="목요일";
                    break;
                case FRIDAY:
                    dayOf="금요일";
                    break;
                case SATURDAY:
                    dayOf="토요일";
                    break;
            }
            daysFoodResponseList.add(
                DaysFoodResponse.builder()
                        .toDay(food.getToDay())
                        .dayOfTheWeek(dayOf)
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
