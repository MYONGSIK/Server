package com.example.myongsick.domain.scrap.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScrapCountResponse {

  private String storeId;
  private int scrapCount;

  public static ScrapCountResponse toDto(CountResponse countResponse) {
    return ScrapCountResponse.builder()
        .storeId(countResponse.getStoreId())
        .scrapCount(countResponse.getScrapCount())
        .build();
  }
}
