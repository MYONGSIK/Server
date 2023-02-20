package com.example.myongsick.domain.scrap.service;

import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScrapService {

  Page<ScrapResponse> getScrapList(String phoneId, Pageable pageable);
}
