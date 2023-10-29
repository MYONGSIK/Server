package com.example.myongsick.domain.mark;

import com.example.myongsick.domain.mark.service.StoreMarkService;
import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.global.object.ApplicationResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v3/marks")
public class StoreMarkController {

  private final StoreMarkService storeMarkService;

  @GetMapping
  @ApiOperation(value = "가게별 담은 유저 수 조회 \n"
      + "")
  public ApplicationResponse<Page<ScrapCountResponse>> getStoreMarkCntList(
      @Param("campus") String campus,
      @RequestParam(value = "offset", defaultValue = "0")
      @Schema(title = "페이지 번호", example = "0", description = "0부터 시작합니다")
      Integer offset,
      @RequestParam(value = "limit", defaultValue = "10")
      @Schema(
          title = "Data 갯수",
          example = "10",
          description = "한 페이지에 보여지는 데이터 수 입니다.")
      Integer limit,
      @RequestParam(value = "orderBy")
      @Schema(
          title = "정렬 기준",
          description = "정렬 기준은 distance (거리) | scrapCount (찜꽁수)가 있습니다..",
          allowableValues = {
              "distance",
              "scrapCount",
          })
      String orderBy
  ) {
    return ApplicationResponse.ok(storeMarkService.getMarkCount(campus, PageRequest.of(offset, limit), orderBy));
  }

  @PostMapping
  @ApiOperation(value = "찜꽁 리스트 추가")
  public ApplicationResponse<Void> createMark(
      @RequestBody @Valid MarkRequest request
  ) {
    storeMarkService.createMark(request.getPhoneId(), request.getCode());
    return ApplicationResponse.ok();
  }

  @DeleteMapping
  @ApiOperation(value = "찜꽁 리스트 삭제")
  public ApplicationResponse<Void> deleteMark(
      @RequestBody @Valid MarkRequest request
  ) {
    storeMarkService.deleteMark(request.getPhoneId(), request.getCode());
    return ApplicationResponse.ok();
  }

  @GetMapping("/my")
  @ApiOperation(value = "유저 찜꽁 리스트 조회")
  public ApplicationResponse<Page<ScrapResponse>> getMyMarkList(
      @RequestParam String phoneId,
      @RequestParam(value = "offset", defaultValue = "0")
      @Schema(title = "페이지 번호", example = "0", description = "0부터 시작합니다")
      Integer offset,
      @RequestParam(value = "limit", defaultValue = "10")
      @Schema(
          title = "Data 갯수",
          example = "10",
          description = "한 페이지에 보여지는 데이터 수 입니다.")
      Integer limit
  ) {
    return ApplicationResponse.ok(storeMarkService.getMarkList(phoneId, PageRequest.of(offset, limit)));
  }
}
