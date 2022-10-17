package com.example.myongsick.domain.food.repository;

import com.example.myongsick.domain.food.entity.Dinner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DinnerRepository extends JpaRepository<Dinner,Long> {

}
