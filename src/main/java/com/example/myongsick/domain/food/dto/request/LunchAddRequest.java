package com.example.myongsick.domain.food.dto.request;


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
@ApiModel(description = "중식 추가를 위한 객체")
public class LunchAddRequest {

    private LocalDate toDay;
    private String type; //A형 B형

    private String status; //운영 미운영
    private String lunch1;
    private String lunch2;
    private String lunch3;
    private String lunch4;
    private String lunch5;
    private String lunch6;


}
