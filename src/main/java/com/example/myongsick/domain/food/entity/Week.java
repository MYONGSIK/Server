package com.example.myongsick.domain.food.entity;

import com.example.myongsick.domain.v2.Meal.entity.Meal;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Week {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startDay;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endDay;

    @OneToMany(mappedBy = "week", fetch = FetchType.LAZY)
    private List<Dinner> dinners = new ArrayList<>();

    @OneToMany(mappedBy = "week", fetch = FetchType.LAZY)
    private List<Lunch> lunches = new ArrayList<>();

    @OneToMany(mappedBy = "week", fetch = FetchType.LAZY)
    private List<Meal> mealList = new ArrayList<>();

    @Builder
    public Week(LocalDate startDay, LocalDate endDay) {
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public void addMeal(Meal meal) {
        this.mealList.add(meal);
    }
}
