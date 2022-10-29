package com.example.myongsick.domain.food.repository;

import com.example.myongsick.domain.food.entity.Dinner;
import com.example.myongsick.domain.food.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DinnerRepository extends JpaRepository<Dinner,Long> {

    Optional<Dinner> findByToDay(LocalDate localDate);

    List<Dinner> findByWeek(Week week);


}
