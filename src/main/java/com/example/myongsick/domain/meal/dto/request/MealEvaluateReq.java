package com.example.myongsick.domain.meal.dto.request;

import com.example.myongsick.domain.meal.entity.MealEvaluate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@ApiModel(description = "음식에 대한 평가에 대한 Request 객체")
public class MealEvaluateReq {

    @NotNull(message = "음식에 대한 번호 값은 필수입니다.")
    private Long mealId;

    @ApiModelProperty(value = "LOVE/HATE", example = "LOVE/HATE", required = true, dataType = "String")
    @NotNull(message = "음식에 대한 평가 값은 필수입니다.")
    private MealEvaluate mealEvaluate;

    @ApiModelProperty(value = "plus/minus", example = "plus/minus", required = true, dataType = "String")
    @NotNull(message = "음식에 대한 평가 값은 필수입니다.")
    private String calculation;

}
