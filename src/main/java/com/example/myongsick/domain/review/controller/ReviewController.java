package com.example.myongsick.domain.review.controller;

import com.example.myongsick.domain.review.dto.ReviewRequest;
import com.example.myongsick.domain.review.dto.ReviewResponse;
import com.example.myongsick.domain.review.service.ReviewService;
import com.example.myongsick.global.object.ApplicationResponse;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/reviews")
public class ReviewController {

  private final ReviewService reviewService;

  @GetMapping
  @ApiOperation(value = "리뷰 전체 조회")
  public ApplicationResponse<Page<ReviewResponse>> getReviewLists(
      Pageable pageable
  ){
    return ApplicationResponse.ok(reviewService.getReviewLists(pageable));
  }

  @GetMapping("/{reviewId}")
  @ApiOperation(value = "리뷰 상세 조회")
  public ApplicationResponse<ReviewResponse> getReview(
      @PathVariable Long reviewId
  ) {
    return ApplicationResponse.ok(reviewService.getReview(reviewId));
  }

  @PostMapping
  @ApiOperation(value = "리뷰 생성"
      + " ( 리뷰 생성 시에는 user 등록이 선행되어야 합니다. "
      + "writerId 에는 user 등록에 사용된 phoneId를 입력해주세요. )"
      + " 리뷰를 작성하기 원하는 날짜 형식은 yyyy-MM-dd 입니다. ")
  public ApplicationResponse<ReviewResponse> createReview(
      @RequestBody @Valid ReviewRequest request
  ){
    return ApplicationResponse.ok(reviewService.createReview(request));
  }
}
