package com.example.myongsick.domain.scrap.controller;

import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapRequest;
import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.domain.scrap.service.ScrapService;
import com.example.myongsick.global.object.ApplicationResponse;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/scraps")
@RequiredArgsConstructor
public class ScarpController {

  private final ScrapService scrapService;

  @GetMapping("/{phoneId}")
  @ApiOperation(value = "유저 찜꽁 리스트 조회")
  public ApplicationResponse<Page<ScrapResponse>> getScrapList(
      @PathVariable String phoneId,
      Pageable pageable
  ) {
    return ApplicationResponse.ok(scrapService.getScrapList(phoneId, pageable));
  }

  @PostMapping
  @ApiOperation(value = "찜꽁 리스트 추가")
  public ApplicationResponse<ScrapResponse> createScrap(
    @RequestBody @Valid ScrapRequest request
  ) {
    return ApplicationResponse.ok(scrapService.createScrap(request));
  }

  @DeleteMapping("/{scrapId}")
  @ApiOperation(value = "찜꽁 리스트 삭제")
  public ApplicationResponse<Void> deleteScrap(
      @PathVariable Long scrapId
  ) {
    scrapService.deleteScrap(scrapId);
    return ApplicationResponse.ok();
  }

  @GetMapping("/store")
  @ApiOperation(value = "가게별 담은 유저 수 조회 \n"
      + "[인기순|거리순] 에 대해 정렬을 지원합니다. \n"
      + "인기순 -> api/v2/scraps/store?sort=scrapCount,desc&campus=YONGIN \n"
      + "거리순 -> api/v2/scraps/store?sort=distance,asc&campus=YONGIN \n"
      + "예시와 같이 요청을 보내주세요.")
  public ApplicationResponse<Page<ScrapCountResponse>> getScrapCount(
      @Param("campus") String campus,
      Pageable pageable
  ) {
    return ApplicationResponse.ok(scrapService.getScrapCount(campus, pageable));
  }
}
