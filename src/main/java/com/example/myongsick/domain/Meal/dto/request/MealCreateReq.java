package com.example.myongsick.domain.v2.Meal.dto.request;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ApiModel(description = "음식 등록 ")
public class MealCreateReq {

    @NotNull(message = "날짜 값은 필수입니다.")
    private LocalDate offeredAt;

    @ApiModelProperty(value = "중식A/중식B/석식", example = "LUNCH_A/LUNCH_B/DINNER", required = true, dataType = "String")
    @NotNull(message = "메뉴의 분류 값은 필수입니다.")
    private String type; //A형 B형

    @ApiModelProperty(value = "운영/미운영", example = "OPEN/CLOSE", required = true, dataType = "String")
    @NotNull(message = "운영 상태 값은 필수입니다.")
    private String status; //OPEN CLOSE

    @ApiModelProperty(value = "MCC식당/생활관식당/교직원식당", example = "MCC식당/생활관식당/교직원식당", required = true, dataType = "String")
    @NotNull(message = "식당 지역 값은 필수입니다.")
    private String area;

    @NotNull(message = "메뉴 값은 필수입니다.")
    private List<String> meals;
}
