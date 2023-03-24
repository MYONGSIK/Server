package com.example.myongsick.domain.dish.controller;

import com.example.myongsick.domain.dish.dto.DishRequest;
import com.example.myongsick.domain.dish.dto.DishResponse;
import com.example.myongsick.domain.dish.service.DishService;
import com.example.myongsick.domain.dish.dto.HateRequest;
import com.example.myongsick.domain.dish.dto.LoveRequest;
import com.example.myongsick.domain.dish.dto.RestaurantRequest;
import com.example.myongsick.global.object.ApplicationResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/dish")
public class DishController {

  private final DishService dishService;

  @GetMapping
  public ApplicationResponse<List<DishResponse>> getDishAll() {
    return ApplicationResponse.ok(dishService.getDishAll());
  }

  @PostMapping
  public ApplicationResponse<String> createDish(
      @RequestBody DishRequest request
  ) {
    return ApplicationResponse.ok(dishService.createDish(request));
  }

  @PostMapping("/restaurant")
  public ApplicationResponse<String> createRestaurant(
      @RequestBody RestaurantRequest request
  ) {
    return ApplicationResponse.ok(dishService.createRestaurant(request));
  }

  @PutMapping("/love")
  public ApplicationResponse<Integer> calculateLoveCount(
      @RequestBody LoveRequest request
  ) {
    return ApplicationResponse.ok(dishService.calculateLoveCount(request));
  }
  @PutMapping("/hate")
  public ApplicationResponse<Integer> calculateHateCount(
      @RequestBody HateRequest request
  ) {
    return ApplicationResponse.ok(dishService.calculateHateCount(request));
  }
}
