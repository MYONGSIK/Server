package com.example.myongsick.domain.meal.entity;

import com.example.myongsick.domain.food.entity.Week;
import com.example.myongsick.domain.meal.entity.MealType;
import com.example.myongsick.domain.meal.entity.StatusType;
import com.example.myongsick.domain.meal.entity.Area;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private MealType mealType;

  @Enumerated(EnumType.STRING)
  private StatusType statusType;

  private String menu1;
  private String menu2;
  private String menu3;
  private String menu4;
  private String menu5;
  private String menu6;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
  private LocalDate offeredAt;

  private int love;
  private int hate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "week_meal_id")
  private Week week;

  @OneToOne
  private Area area;

  @Builder
  public Meal(MealType mealType,
              List<String> menus,
              LocalDate offeredAt,
              Week week,
              Area area,
              StatusType statusType) {
    this.mealType = mealType;
    this.menu1 = menus.get(0);
    this.menu2 = menus.get(1);
    this.menu3 = menus.get(2);
    this.menu4 = menus.get(3);
    this.menu5 = menus.get(4);
    this.menu6 = menus.get(5);
    this.statusType = statusType;
    this.offeredAt = offeredAt;
    this.love = 0;
    this.hate = 0;
    this.addWeek(week);
    this.area = area;
  }

  public void addWeek(Week week){
    this.week = week;
    week.addMeal(this);
  }

  public void addLove(){
    this.love++;
  }
  public void reduceLove(){
    this.love--;
  }
  public void addHate(){
    this.hate++;
  }
  public void reduceHate(){
    this.hate--;
  }

}
