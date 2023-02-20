package com.example.myongsick.domain.review.service;

import com.example.myongsick.domain.v2.Meal.entity.Meal;
import com.example.myongsick.domain.review.dto.ReviewRequest;
import com.example.myongsick.domain.review.dto.ReviewResponse;
import com.example.myongsick.domain.review.entity.Review;
import com.example.myongsick.domain.review.exception.ReviewNotFoundException;
import com.example.myongsick.domain.review.repository.ReviewRepository;
import com.example.myongsick.domain.user.User;
import com.example.myongsick.domain.user.UserRepository;
import com.example.myongsick.domain.v2.Meal.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService{

  private final ReviewRepository reviewRepository;
  private final UserRepository userRepository;
  private final MealRepository mealRepository;

  @Override
  public Page<ReviewResponse> getReviewLists(Pageable pageable) {
    return reviewRepository.findAll(pageable).map(ReviewResponse::toDto);
  }

  @Override
  public ReviewResponse getReview(Long reviewId) {
    Review review = reviewRepository.findById(reviewId).orElseThrow(ReviewNotFoundException::new);
    return ReviewResponse.toDto(review);
  }

  @Override
  @Transactional
  public ReviewResponse createReview(ReviewRequest request) {
    User user = userRepository.findByPhoneId(request.getWriterId()).get();
    Meal meal = mealRepository.findById(request.getMealId()).get();
    Review review = reviewRepository.save(request.toEntity(user, meal, request.getContent()));
    return ReviewResponse.toDto(review);
  }
}
