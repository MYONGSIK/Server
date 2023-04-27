package com.example.myongsick.domain.scrap.repository;

import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

public interface ScrapRepositoryCustom {
  Page<ScrapCountResponse> findAllByCampusWithPaging(@Param("campus") String campus, Pageable pageable);

  ScrapCountResponse findByIdCustom(Long storeId);
}
