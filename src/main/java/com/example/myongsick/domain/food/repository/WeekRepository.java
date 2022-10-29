package com.example.myongsick.domain.food.repository;

import com.example.myongsick.domain.food.entity.Week;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface WeekRepository extends JpaRepository<Week,Long> {
//    Optional<Week> findByStartDayBetweenAndEndDay(String toDay);

//        Optional<Week> findByStartDayGreaterThanEqualAndEndDayLessThanEqual(LocalDate Day, LocalDate end);
//    Optional<Week> findById(Long id);
//    Optional<Week> findByStartDayBetween(LocalDate startDate);

    Optional<Week> findByStartDayLessThanEqualAndEndDayGreaterThanEqual(LocalDate start, LocalDate end);

}
