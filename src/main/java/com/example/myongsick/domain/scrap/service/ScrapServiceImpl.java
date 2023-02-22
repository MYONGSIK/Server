package com.example.myongsick.domain.scrap.service;

import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapRequest;
import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.domain.scrap.entity.Scrap;
import com.example.myongsick.domain.scrap.exception.AlreadyScrapException;
import com.example.myongsick.domain.scrap.exception.NotFoundScrapException;
import com.example.myongsick.domain.scrap.exception.ScrapException;
import com.example.myongsick.domain.scrap.repository.ScrapRepository;
import com.example.myongsick.domain.user.entity.User;
import com.example.myongsick.domain.user.exception.NotFoundUserException;
import com.example.myongsick.domain.user.repository.UserRepository;
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
    User user = userRepository.findByPhoneId(phoneId).orElseThrow(NotFoundUserException::new);
    return scrapRepository.findAllByUser(user, pageable).map(ScrapResponse::toDto);
  }

  @Override
  @Transactional
  public ScrapResponse createScrap(ScrapRequest request) {
    User user = userRepository.findByPhoneId(request.getPhoneId()).orElseThrow(NotFoundUserException::new);
    if (scrapRepository.findByStoreId(request.getStoreId()).isPresent())
      throw new AlreadyScrapException();
    Scrap scrap = scrapRepository.save(Scrap.builder().storeId(request.getStoreId()).user(user).build());
    return ScrapResponse.toDto(scrap);
  }

  @Override
  @Transactional
  public void deleteScrap(Long scrapId) {
    Scrap scrap = scrapRepository.findById(scrapId).orElseThrow(NotFoundScrapException::new);
    scrapRepository.deleteById(scrap.getId());
  }

  @Override
  public Page<ScrapCountResponse> getScrapCount( Pageable pageable) {

    return scrapRepository.findAllCustom(pageable).map(ScrapCountResponse::toDto);
  }
}
