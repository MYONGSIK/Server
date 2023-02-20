package com.example.myongsick.domain.food.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.myongsick.domain.food.repository.DinnerRepository;
import com.example.myongsick.domain.food.repository.LunchRepository;
import com.example.myongsick.domain.food.repository.WeekRepository;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FoodServiceImplTest {

  @InjectMocks
  FoodServiceImpl foodService;
  @Mock
  WeekRepository weekRepository;
  @Mock DinnerRepository dinnerRepository;
  @Mock LunchRepository lunchRepository;

  @Test
  @DisplayName("주간 식단 조회시, 값이 없는 경우 - 예외")
  void 주간식단조회_NoSuchElement() {
    // given

    // when

    // then
   assertThrows(NoSuchElementException.class, () -> foodService.getWeekFoods());
  }

//  @Test
//  @DisplayName("주간 식단 조회시, 값이 없는 경우 - 실패")
//  void 주간식단조회_실패() {
//    assertThrows(IndexOutOfBoundsException.class, () -> foodService.getWeekFoods());
//  }
}