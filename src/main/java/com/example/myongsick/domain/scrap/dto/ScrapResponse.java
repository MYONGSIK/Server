package com.example.myongsick.domain.scrap.dto;

import com.example.myongsick.domain.v2.Scrap;
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
  private String storeId;
  private int scrapCnt; // store 별로 scarp 된 개수
  public static ScrapResponse toDto(Scrap scrap) {
    return ScrapResponse.builder()
        .storeId(scrap.getStoreId())
        .scrapCnt(0)
        .build();
  }
}
