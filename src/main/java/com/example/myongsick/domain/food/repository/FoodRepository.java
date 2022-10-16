package com.example.myongsick.domain.food.repository;

import com.example.myongsick.domain.food.entity.Food;
import com.example.myongsick.domain.food.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food,Long> {
    List<Food> findByWeek(Week week);

    List<Food> findByToDay(LocalDate toDay);

    Optional<Food> findByToDayAndClassification(LocalDate toDay, String classification);
    List<Food> findByWeekAndClassification(Week week, String classification);
}
