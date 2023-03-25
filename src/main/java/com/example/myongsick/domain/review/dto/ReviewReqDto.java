package com.example.myongsick.domain.review.dto;

import lombok.Getter;

@Getter
public class ReviewReqDto {
  private String writerId;
  private String registeredAt;
  private String content;
  private String areaName;
}
