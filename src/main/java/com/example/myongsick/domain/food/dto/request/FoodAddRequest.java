package com.example.myongsick.domain.food.dto.request;


import com.example.myongsick.domain.food.dto.response.DaysFoodResponse;
import com.example.myongsick.domain.food.entity.Food;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "음식 추가를 위한 객체")
public class FoodAddRequest {

    private LocalDate toDay;

    private String classification; //중식 석식
    private String status; //운영 미운영
    private String food1;
    private String food2;
    private String food3;
    private String food4;
    private String food5;
    private String food6;



}
