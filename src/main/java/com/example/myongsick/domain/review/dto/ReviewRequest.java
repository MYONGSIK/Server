package com.example.myongsick.domain.review.dto;

import com.example.myongsick.domain.meal.entity.Meal;
import com.example.myongsick.domain.review.entity.Review;
import com.example.myongsick.domain.user.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "리뷰 생성을 위한 객체")
public class ReviewRequest {

  @ApiModelProperty(value = "유저 핸드폰 ID", required = true, dataType = "String")
  @NotNull(message = "유저 핸드폰 ID를 입력해주세요")
  @NotBlank(message = "유저 핸드폰 ID를 입력해주세요")
  private String writerId;

  @ApiModelProperty(value = "식사 ID", required = true, dataType = "number")
  @NotNull(message = "식사 ID를 입력해주세요")
  private Long mealId;

  @ApiModelProperty(value = "식사 리뷰", required = true, dataType = "String")
  @NotNull(message = "식사에 대한 리뷰를 입력해주세요")
  @NotBlank(message = "식사에 대한 리뷰를 입력해주세요")
  private String content;

  public Review toEntity(User user, Meal meal, String content) {
    return Review.builder()
        .user(user)
        .meal(meal)
        .content(content)
        .build();
  }
}
