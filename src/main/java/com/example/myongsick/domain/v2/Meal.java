package com.example.myongsick.domain.v2;

import com.example.myongsick.domain.v2.Week;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Getter;

@Entity
@Getter
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

  private LocalDate offeredAt;

  private int love;
  private int hate;

  @ManyToOne
  private Week week;

  @OneToOne
  private Area area;
}
