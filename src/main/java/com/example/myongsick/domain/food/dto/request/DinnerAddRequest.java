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
@ApiModel(description = "석식 추가를 위한 객체")
public class DinnerAddRequest {

    private LocalDate toDay;

    private String status; //운영 미운영
    private String dinner1;
    private String dinner2;
    private String dinner3;
    private String dinner4;
    private String dinner5;
    private String dinner6;



}
