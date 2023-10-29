package com.example.myongsick.domain.scrap.dto;

import com.example.myongsick.domain.scrap.entity.Scrap;
import com.querydsl.core.annotations.QueryProjection;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel(description = "찜꽁 리스트 응답 객체")
public class ScrapResponse {

  private Long id;
  private String code;
  private String name;
  private String distance;
  private String category;
  private String address;
  private String contact;
  private String urlAddress;
  private String latitude;
  private String longitude;

  public static ScrapResponse toDto(Scrap scrap) {
    return ScrapResponse.builder()
        .id(scrap.getId())
        .code(scrap.getStore().getCode())
        .name(scrap.getStore().getName())
        .distance(scrap.getStore().getDistance())
        .category(scrap.getStore().getCategory())
        .address(scrap.getStore().getAddress())
        .contact(scrap.getStore().getContact())
        .urlAddress(scrap.getStore().getUrlAddress())
        .latitude(scrap.getStore().getLatitude())
        .longitude(scrap.getStore().getLongitude())
        .build();
  }

  @QueryProjection
  public ScrapResponse(
      Long id,
      String code,
      String name,
      String category,
      String address,
      String contact,
      String urlAddress,
      String latitude,
      String longitude
  ) {
    this.id = id;
    this.code = code;
    this.name = name;
    this.category = category;
    this.address = address;
    this.contact = contact;
    this.urlAddress = urlAddress;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
