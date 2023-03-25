package com.example.myongsick.domain.dish.repository;

import com.example.myongsick.domain.dish.entity.Hate;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HateRepository extends MongoRepository<Hate, String> {
  Boolean existsByDishIdAndUserId(String dishId, String userId);
  void deleteByDishIdAndUserId(String dishId, String userId);
}
