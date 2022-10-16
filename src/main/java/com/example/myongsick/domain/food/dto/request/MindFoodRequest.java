package com.example.myongsick.domain.food.dto.request;

import com.example.myongsick.domain.food.entity.Food;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "유저 음식 관련한 마음을 표시")
public class MindFoodRequest {

    private LocalDate toDay;
    private String classification;
    private String mind;
    private String calculation;

}
