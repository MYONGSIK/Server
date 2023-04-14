package com.example.myongsick.domain.review.service;

import com.example.myongsick.domain.review.dto.ReviewReqDto;
import com.example.myongsick.domain.review.dto.ReviewRequest;
import com.example.myongsick.domain.review.dto.ReviewResponse;
import com.example.myongsick.domain.review.entity.Review;
import com.example.myongsick.domain.review.exception.ReviewNotFoundException;
import com.example.myongsick.domain.review.repository.ReviewRepository;
import com.example.myongsick.domain.user.entity.User;
import com.example.myongsick.domain.user.exception.NotFoundUserException;
import com.example.myongsick.domain.user.repository.UserRepository;
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
    User user = userRepository.findByPhoneId(request.getWriterId()).orElseThrow(NotFoundUserException::new);
    Review review = reviewRepository.save(request.toEntity(user, request.getRegisteredAt(), request.getContent()));
    return ReviewResponse.toDto(review);
  }

  @Override
  @Transactional
  public ReviewResponse createReviewWithArea(ReviewReqDto request) {
    User user = userRepository.findByPhoneId(request.getWriterId()).orElseThrow(NotFoundUserException::new);
    Review review = reviewRepository.save(Review.builder().area(request.getAreaName()).user(user).content(request.getContent()).registeredAt(request.getRegisteredAt()).build());
    return ReviewResponse.toDto(review);
  }
}
