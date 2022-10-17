package com.example.myongsick.domain.food.repository;

import com.example.myongsick.domain.food.entity.Lunch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchRepository extends JpaRepository<Lunch, Long> {
}
