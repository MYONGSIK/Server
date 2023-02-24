package com.example.myongsick.domain.meal.dto.response;


import com.example.myongsick.domain.meal.entity.Meal;
import com.example.myongsick.domain.meal.entity.MealType;
import com.example.myongsick.domain.meal.entity.StatusType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "주 단위 음식 조회")
public class MealResponse {

    private Long mealId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate toDay;
    private MealType mealType;
    private StatusType statusType;
    private List<String> meals;

    public static List<MealResponse> toEntity(List<Meal> meals) {
        return meals.stream().map(meal -> MealResponse.builder()
                .mealId(meal.getId())
                .toDay(meal.getOfferedAt())
                .statusType(meal.getStatusType())
                .mealType(meal.getMealType())
                .meals(List.of(meal.getMenu1(), meal.getMenu2(), meal.getMenu3(), meal.getMenu4(), meal.getMenu5(),meal.getMenu6()))
                .build()).collect(Collectors.toList());
    }
}
