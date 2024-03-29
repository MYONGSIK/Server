package com.example.myongsick.domain.review.dto;

import com.example.myongsick.domain.review.entity.Review;
import io.swagger.annotations.ApiModel;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "리뷰 응답 객체")
public class ReviewResponse {

  private Long reviewId;
  private String writerId;
  private String content;
  private LocalDate createdAt;
  private LocalDate updatedAt;

  public static ReviewResponse toDto(Review review) {
    return ReviewResponse.builder()
        .reviewId(review.getId())
        .writerId(review.getUser().getPhoneId())
        .content(review.getContent())
        .createdAt(review.getCreatedAt())
        .updatedAt(review.getUpdatedAt())
        .build();
  }
}
