package com.example.myongsick.domain.scrap.dto;

import io.swagger.annotations.ApiModel;
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
@ApiModel(description = "찜꽁 리스트 응답 객체 ( + 찜꽁수 포함 )")
public class ScrapCountResponse {

  private Long storeId;
  private String code;
  private String name;
  private String category;
  private String address;
  private String urlAddress;
  private String distance;
  private String contact;
  private String latitude;
  private String longitude;
  private int scrapCount;

  public static ScrapCountResponse toDto(CountResponse countResponse) {
    return ScrapCountResponse.builder()
        .storeId(countResponse.getStoreId())
        .code(countResponse.getCode())
        .name(countResponse.getName())
        .category(countResponse.getCategory())
        .address(countResponse.getAddress())
        .urlAddress(countResponse.getUrlAddress())
        .distance(countResponse.getDistance())
        .scrapCount(countResponse.getScrapCount())
        .contact(countResponse.getContact())
        .latitude(countResponse.getLatitude())
        .longitude(countResponse.getLongitude())
        .build();
  }
}
