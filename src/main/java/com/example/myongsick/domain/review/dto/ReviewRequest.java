package com.example.myongsick.domain.review.dto;

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

//  @ApiModelProperty(value = "유저 핸드폰 ID", required = true, dataType = "String")
  @NotNull(message = "유저 핸드폰 ID를 입력해주세요")
  @NotBlank(message = "유저 핸드폰 ID를 입력해주세요")
  private String writerId;

//  @ApiModelProperty(value = "리뷰 날짜", required = true, dataType = "String")
  @NotNull(message = "리뷰를 남길 날짜를 입력해주세요")
  @NotBlank(message = "리뷰를 남길 날짜를 입력해주세요")
  private String registeredAt;

//  @ApiModelProperty(value = "식사 리뷰", required = true, dataType = "String")
  @NotNull(message = "식사에 대한 리뷰를 입력해주세요")
  @NotBlank(message = "식사에 대한 리뷰를 입력해주세요")
  private String content;

  public Review toEntity(User user, String registeredAt, String content) {
    return Review.builder()
        .user(user)
        .registeredAt(registeredAt)
        .content(content)
        .build();
  }
}
