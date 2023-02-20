package com.example.myongsick.domain.review.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import com.example.myongsick.domain.review.dto.ReviewRequest;
import com.example.myongsick.domain.review.dto.ReviewResponse;
import com.example.myongsick.domain.review.entity.Review;
import com.example.myongsick.domain.review.repository.ReviewRepository;
import com.example.myongsick.domain.v2.Meal.entity.Meal;
import com.example.myongsick.domain.meal.MealRepository;
import com.example.myongsick.domain.user.User;
import com.example.myongsick.domain.user.UserRepository;
import com.example.myongsick.domain.v2.Area;
import com.example.myongsick.domain.v2.MealType;
import com.example.myongsick.domain.v2.WeekMeal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {
  @InjectMocks
  ReviewServiceImpl reviewService;
  @Mock
  ReviewRepository reviewRepository;
  @Mock
  UserRepository userRepository;
  @Mock
  MealRepository mealRepository;

  @Test
  @DisplayName("리뷰 전체 조회")
  void 리뷰조회_성공() {
    // given
    Review review = createReview("맛있어요");

    // when
    lenient().when(reviewRepository.findById(anyLong())).thenReturn(Optional.of(review));
    ReviewResponse response = reviewService.getReview(1L);

    // then
    assertAll(
        () -> assertEquals(response.getContent(), review.getContent())
    );
  }

  @Test
  void 리뷰생성_성공() {
    // given
    User user = createUser();
    Meal meal = createMeal();
    ReviewRequest request = ReviewRequest.builder()
        .mealId(1L)
        .writerId(user.getPhoneId())
        .content("맛있어요")
        .build();
    Review review = createReview(request.getContent());

    // when
    lenient().when(userRepository.findByPhoneId(user.getPhoneId())).thenReturn(Optional.of(user));
    lenient().when(mealRepository.findById(anyLong())).thenReturn(Optional.of(meal));
    when(reviewRepository.save(any())).thenReturn(review);

    ReviewResponse response = reviewService.createReview(request);

    // then
    assertEquals(response.getContent(), request.getContent());
  }

  Review createReview(String content){
    return Review.builder()
        .user(createUser())
        .meal(createMeal())
        .content(content)
        .build();
  }
  User createUser(){
    return User.builder()
        .phoneId("같은 유저")
        .build();
  }
  Meal createMeal(){
    return Meal.builder()
        .mealType(MealType.LUNCH_A)
        .menus(List.of(new String[]{"보리밥", "된장국", "김치", "시금치", "생선구이", "귤"}))
        .offeredAt(LocalDate.of(2023, 3, 1))
        .weekMeal(WeekMeal.builder().startDate(LocalDate.now()).endDate(LocalDate.now()).build())
        .area(Area.builder().id(1L).build())
        .build();
  }
}