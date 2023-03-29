//package com.example.myongsick.domain.dish.dto;
//
//import com.example.myongsick.domain.dish.entity.Dish;
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//@Getter
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class DishResponse {
//  private String dishId;
//  private String toDay;
//  private String mealType;
//  private String statusType;
//  private List<String> menu;
//
//  public static DishResponse toDto(Dish dish) {
//    return DishResponse.builder()
//        .dishId(dish.get_id())
//        .toDay(dish.getOfferedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
//        .mealType(dish.getMealType().name())
//        .statusType(dish.getStatusType().name())
//        .menu(dish.getMenus())
//        .build();
//  }
//}
