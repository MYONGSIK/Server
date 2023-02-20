package com.example.myongsick.domain.review.service;

import com.example.myongsick.domain.review.dto.ReviewRequest;
import com.example.myongsick.domain.review.dto.ReviewResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {

  Page<ReviewResponse> getReviewLists(Pageable pageable);

  ReviewResponse getReview(Long reviewId);

  ReviewResponse createReview(ReviewRequest request);
}
