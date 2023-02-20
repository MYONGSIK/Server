package com.example.myongsick.domain.scrap.controller;

import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.domain.scrap.service.ScrapService;
import com.example.myongsick.global.object.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/scraps")
@RequiredArgsConstructor
public class ScarpController {

  private final ScrapService scrapService;

  @GetMapping("/{phoneId}")
  public ApplicationResponse<Page<ScrapResponse>> getScrapList(
      @PathVariable String phoneId,
      Pageable pageable
  ) {
    return ApplicationResponse.ok(scrapService.getScrapList(phoneId, pageable));
  }
}
