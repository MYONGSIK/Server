package com.example.myongsick.domain.scrap.service;

import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.domain.scrap.repository.ScrapRepository;
import com.example.myongsick.domain.user.User;
import com.example.myongsick.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ScrapServiceImpl implements ScrapService{
  private final UserRepository userRepository;
  private final ScrapRepository scrapRepository;
  @Override
  public Page<ScrapResponse> getScrapList(String phoneId, Pageable pageable) {
    // 유저 조회
    User user = userRepository.findByPhoneId(phoneId).get();
    return scrapRepository.findAllByUserCustom(user, pageable);
  }
}
