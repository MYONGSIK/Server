package com.example.myongsick.domain.food.repository;

import com.example.myongsick.domain.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food,Long> {

}
