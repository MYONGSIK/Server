package com.example.myongsick.domain.v2;

import com.example.myongsick.domain.meal.Meal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class WeekMeal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate startDate;

  private LocalDate endDate;

  @OneToMany(mappedBy = "week", cascade = CascadeType.ALL)
  private List<Meal> mealList = new ArrayList<>();

  @Builder
  public WeekMeal(LocalDate startDate,
      LocalDate endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public void addMeal(Meal meal) {
    this.mealList.add(meal);
  }
}
