package com.example.myongsick.domain.mark.service;

import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoreMarkService {
  Page<ScrapResponse> getMarkList(String phoneId, Pageable pageable);
  void createMark(String phoneId, String storeCode);
  void deleteMark(String phoneId, String storeCode);
  Page<ScrapCountResponse> getMarkCount(String campus, Pageable pageable, String orderBy);
}
