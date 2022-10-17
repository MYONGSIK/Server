package com.example.myongsick.domain.food.repository;

import com.example.myongsick.domain.food.entity.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LunchRepository extends JpaRepository<Lunch, Long> {

    List<Lunch> findByToDay(LocalDate localDate);

    Optional<Lunch> findByToDayAndType(LocalDate localDate, String type);
}
