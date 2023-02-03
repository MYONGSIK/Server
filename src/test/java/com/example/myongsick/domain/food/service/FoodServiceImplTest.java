package com.example.myongsick.domain.food.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.example.myongsick.domain.food.dto.response.WeekFoodResponse;
import com.example.myongsick.domain.food.repository.DinnerRepository;
import com.example.myongsick.domain.food.repository.LunchRepository;
import com.example.myongsick.domain.food.repository.WeekRepository;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class FoodServiceImplTest {

  @Autowired FoodServiceImpl foodService;
  @Autowired WeekRepository weekRepository;
  @Autowired DinnerRepository dinnerRepository;
  @Autowired LunchRepository lunchRepository;

  @Test
  @DisplayName("주간 식단 조회시, 값이 없는 경우 - 예외")
  void 주간식단조회_NoSuchElement() {
    // given

    // when

    // then
   assertThrows(NoSuchElementException.class, () -> foodService.getWeekFoods());
  }
}