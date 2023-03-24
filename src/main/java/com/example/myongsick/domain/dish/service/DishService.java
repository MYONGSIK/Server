package com.example.myongsick.domain.dish.service;

import com.example.myongsick.domain.dish.repository.DishRepository;
import com.example.myongsick.domain.dish.repository.HateRepository;
import com.example.myongsick.domain.dish.repository.LoveRepository;
import com.example.myongsick.domain.dish.repository.RestaurantRepository;
import com.example.myongsick.domain.dish.dto.RestaurantRequest;
import com.example.myongsick.domain.dish.dto.DishRequest;
import com.example.myongsick.domain.dish.dto.DishResponse;
import com.example.myongsick.domain.dish.dto.HateRequest;
import com.example.myongsick.domain.dish.dto.LoveRequest;
import com.example.myongsick.domain.dish.entity.Dish;
import com.example.myongsick.domain.dish.entity.Hate;
import com.example.myongsick.domain.dish.entity.Love;
import com.example.myongsick.domain.dish.entity.Restaurant;
import com.example.myongsick.domain.user.entity.User;
import com.example.myongsick.domain.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DishService {
  private final DishRepository dishRepository;
  private final RestaurantRepository restaurantRepository;
  private final LoveRepository loveRepository;
  private final HateRepository hateRepository;
  private final UserRepository userRepository;

  public List<DishResponse> getDishAll() {
    return dishRepository.findAll().stream().map(DishResponse::toDto).collect(Collectors.toList());
  }

  @Transactional
  public String createDish(DishRequest request) {
    Restaurant findRestaurant = restaurantRepository.findById(request.getRestaurantId()).orElseThrow();
    Dish saved = dishRepository.save(Dish.builder().offeredAt(request.getOfferedAt()).mealType(request.getMealType()).statusType(request.getStatusType()).menus(request.getMenu()).restaurantId(
        findRestaurant.get_id()).build());
    return saved.get_id();
  }

  @Transactional
  public String createRestaurant(RestaurantRequest request) {
    Restaurant saved = restaurantRepository.save(Restaurant.builder().name(request.getName()).campusType(request.getCampusType()).build());
    return saved.get_id();
  }

  @Transactional
  public Integer calculateLoveCount(LoveRequest request) {
    Dish findDish = dishRepository.findById(request.getDishId()).orElseThrow();
    User findUser = userRepository.findByPhoneId(request.getUserId()).orElseThrow();
    if (!loveRepository.existsByDishIdAndUserId(findDish.get_id(), findUser.getPhoneId())) {
      loveRepository.insert(Love.builder().dishId(findDish.get_id()).userId(findUser.getPhoneId()).build());
      findDish.addLoveCount();
    } else {
      findDish.subLoveCount();
      loveRepository.deleteByDishIdAndUserId(findDish.get_id(), findUser.getPhoneId());
    }

    return dishRepository.save(findDish).getLoveCount();
  }

  @Transactional
  public Integer calculateHateCount(HateRequest request) {
    Dish findDish = dishRepository.findById(request.getDishId()).orElseThrow();
    User findUser = userRepository.findByPhoneId(request.getUserId()).orElseThrow();
    if (!hateRepository.existsByDishIdAndUserId(findDish.get_id(), findUser.getPhoneId())) {
      hateRepository.insert(Hate.builder().dishId(findDish.get_id()).userId(findUser.getPhoneId()).build());
      findDish.addHateCount();
    } else {
      findDish.subHateCount();
      hateRepository.deleteByDishIdAndUserId(findDish.get_id(), findUser.getPhoneId());
    }

    return dishRepository.save(findDish).getLoveCount();
  }
}
