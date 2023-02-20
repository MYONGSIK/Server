package com.example.myongsick.domain.Meal.repository;

import com.example.myongsick.domain.Meal.entity.Area;
import com.example.myongsick.domain.Meal.entity.Meal;
import com.example.myongsick.domain.food.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal,Long> {

    List<Meal> findByOfferedAtAndArea(LocalDate toDay, Area area);
    List<Meal> findByWeekAndArea(Week week, Area area);
}
