package com.example.myongsick.domain.food.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "주 단위 음식 조회")
public class WeekFoodResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime toDay;

    private String classification; //중식 석식
    private String status; //운영 미운영

    private String food1;
    private String food2;
    private String food3;
    private String food4;
    private String food5;
    private String food6;

    private Long love;
    private Long hate;

}
