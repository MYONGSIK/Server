package com.example.myongsick.domain.mark.repository;

import com.example.myongsick.domain.mark.entity.StoreMark;
import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreMarkRepositoryCustom {

  StoreMark findByUserIdAndStoreId(Long userId, Long storeId);

  Page<ScrapCountResponse> findByCampus(String campus, Pageable pageable, String orderBy);

  Page<ScrapResponse> findByUserId(Long userId, Pageable pageable);

}
