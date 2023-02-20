package com.example.myongsick.domain.meal;

import com.example.myongsick.domain.v2.Area;
import com.example.myongsick.domain.v2.MealType;
import com.example.myongsick.domain.v2.StatusType;
import com.example.myongsick.domain.v2.WeekMeal;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meal {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private MealType mealType;

  private String menu1;
  private String menu2;
  private String menu3;
  private String menu4;
  private String menu5;
  private String menu6;

  private StatusType statusType;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
  private LocalDate offeredAt;

  private int love;
  private int hate;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "week_meal_id")
  private WeekMeal week;

  @OneToOne
  private Area area;

  @Builder
  public Meal(MealType mealType,
      List<String> menus,
      LocalDate offeredAt,
      WeekMeal weekMeal,
      Area area) {
    this.mealType = mealType;
    this.menu1 = menus.get(0);
    this.menu2 = menus.get(1);
    this.menu3 = menus.get(2);
    this.menu4 = menus.get(3);
    this.menu5 = menus.get(4);
    this.menu6 = menus.get(5);
    this.statusType = StatusType.OPEN;
    this.offeredAt = offeredAt;
    this.love = 0;
    this.hate = 0;
    this.addWeek(weekMeal);
    this.area = area;
  }

  public void addWeek(WeekMeal week){
    this.week = week;
    week.addMeal(this);
  }
}
