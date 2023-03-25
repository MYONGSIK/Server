package com.example.myongsick.domain.dish.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;

@Getter
public class DishRequest {
  private LocalDate offeredAt;
  private String mealType;
  private String statusType;
  private String restaurantId;
  private List<String> menu;
}
