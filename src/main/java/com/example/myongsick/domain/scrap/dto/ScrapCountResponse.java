package com.example.myongsick.domain.scrap.dto;

import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@ApiModel(description = "찜꽁 리스트 응답 객체 ( + 찜꽁수 포함 )")
public class ScrapCountResponse {

  private Long storeId;
  private String code;
  private String name;
  private String category;
  private String address;
  private String contact;
  private String urlAddress;
  private String distance;
  private String latitude;
  private String longitude;
  private int scrapCount;
  @QueryProjection
  public ScrapCountResponse(Long storeId, String code, String name, String category, String address,
      String contact, String urlAddress, String distance, String latitude, String longitude,
      int scrapCount) {
    this.storeId = storeId;
    this.code = code;
    this.name = name;
    this.category = category;
    this.address = address;
    this.contact = contact;
    this.urlAddress = urlAddress;
    this.distance = distance;
    this.latitude = latitude;
    this.longitude = longitude;
    this.scrapCount = scrapCount;
  }

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
