//package com.example.myongsick.domain.dish.entity;
//
//import com.example.myongsick.domain.meal.entity.MealType;
//import com.example.myongsick.domain.meal.entity.StatusType;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonFormat.Shape;
//import java.time.LocalDate;
//import java.util.List;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//import org.springframework.data.mongodb.core.mapping.FieldType;
//
//@Getter
//@NoArgsConstructor
//@Document
//public class Dish {
//
//  @Id
//  @Field(value = "_id", targetType = FieldType.OBJECT_ID)
//  private String _id;
//
//  @Field("dish_type")
//  private MealType mealType;
//
//  @Field("status_type")
//  private StatusType statusType;
//
//  private List<String> menus;
//
//  @JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
//  private LocalDate offeredAt;
//
//  private Integer loveCount;
//  private Integer hateCount;
//
//  private String restaurantId;
//
//  @Builder
//  public Dish(
//      String mealType,
//      String statusType,
//      List<String> menus,
//      LocalDate offeredAt,
//      String restaurantId
//  ) {
//    this.mealType = MealType.valueOf(mealType);
//    this.statusType = StatusType.valueOf(statusType);
//    this.menus = menus;
//    this.offeredAt = offeredAt;
//    this.restaurantId = restaurantId;
//    this.loveCount= 0;
//    this.hateCount = 0;
//  }
//  public void addLoveCount() {
//    this.loveCount++;
//    System.out.println("loveCount ->>>>>> " + loveCount);
//  }
//  public void subLoveCount() {
//    this.loveCount--;
//  }
//  public void addHateCount() {
//    this.hateCount++;
//  }
//  public void subHateCount() {
//    this.hateCount--;
//  }
//}
