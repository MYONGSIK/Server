package com.example.myongsick.domain.dish.repository;

import com.example.myongsick.domain.dish.entity.Love;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoveRepository extends MongoRepository<Love, String> {
  Boolean existsByDishIdAndUserId(String dishId, String userId);
  void deleteByDishIdAndUserId(String dishId, String userId);
}
