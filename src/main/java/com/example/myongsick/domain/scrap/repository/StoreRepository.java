package com.example.myongsick.domain.scrap.repository;

import com.example.myongsick.domain.scrap.entity.Store;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {
  Optional<Store> findByCode(@Param("code") String code);
}
