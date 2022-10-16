package com.example.myongsick.domain.food.dto.response;

import com.example.myongsick.domain.food.entity.Food;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    private String lunch1;
    private String lunch2;
    private String lunch3;
    private String lunch4;
    private String lunch5;
    private String lunch6;
    private String dinner1;
    private String dinner2;
    private String dinner3;
    private String dinner4;
    private String dinner5;
    private String dinner6;


    public static List<WeekFoodResponse> toEntity(List<Food> lunch, List<Food> dinner) {
        List<WeekFoodResponse> weekFoodResponses = new ArrayList<>();

        for(int i=0; i<5; i++){
            String dayOf = "";
            switch (lunch.get(i).getToDay().getDayOfWeek()){
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
            weekFoodResponses.add(
                WeekFoodResponse.builder()
                        .toDay(lunch.get(i).getToDay())
                        .status(lunch.get(i).getStatus())
                        .dayOfTheWeek(dayOf)
                        .lunch1(lunch.get(i).getFood1())
                        .lunch2(lunch.get(i).getFood2())
                        .lunch3(lunch.get(i).getFood3())
                        .lunch4(lunch.get(i).getFood4())
                        .lunch5(lunch.get(i).getFood5())
                        .lunch6(lunch.get(i).getFood6())
                        .dinner1(dinner.get(i).getFood1())
                        .dinner2(dinner.get(i).getFood2())
                        .dinner3(dinner.get(i).getFood3())
                        .dinner4(dinner.get(i).getFood4())
                        .dinner5(dinner.get(i).getFood5())
                        .dinner6(dinner.get(i).getFood6())
                        .build()
            );
        }
        return weekFoodResponses;
    }
}
