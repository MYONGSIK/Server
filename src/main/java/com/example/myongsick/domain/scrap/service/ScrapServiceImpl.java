package com.example.myongsick.domain.scrap.service;

import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapRequest;
import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.domain.scrap.entity.CampusType;
import com.example.myongsick.domain.scrap.entity.Scrap;
import com.example.myongsick.domain.scrap.entity.Store;
import com.example.myongsick.domain.scrap.exception.AlreadyScrapException;
import com.example.myongsick.domain.scrap.exception.NotFoundScrapException;
import com.example.myongsick.domain.scrap.repository.ScrapRepository;
import com.example.myongsick.domain.scrap.repository.StoreRepository;
import com.example.myongsick.domain.user.entity.User;
import com.example.myongsick.domain.user.exception.NotFoundUserException;
import com.example.myongsick.domain.user.repository.UserRepository;
import java.util.Optional;
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
  private final StoreRepository storeRepository;
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
    Store store = createStore(request);
    if (scrapRepository.findByStoreAndUser(store, user).isPresent())
      throw new AlreadyScrapException();
    Scrap scrap = scrapRepository.save(Scrap.builder().store(store).user(user).build());
    return ScrapResponse.toDto(scrap);
  }

  private Store createStore(ScrapRequest request) {
    Optional<Store> already = storeRepository.findByCode(request.getCode());
    if (already.isPresent())
      return already.get();
    else {
      Store store = Store.builder().code(request.getCode()).name(request.getName())
          .category(request.getCategory()).distance(request.getDistance()).address(request.getAddress())
          .urlAddress(request.getUrlAddress()).contact(request.getContact()).campus(CampusType.valueOf(request.getCampus())).build();
    return storeRepository.save(store);
    }
  }
  @Override
  @Transactional
  public void deleteScrap(Long scrapId) {
    Scrap scrap = scrapRepository.findById(scrapId).orElseThrow(NotFoundScrapException::new);
    scrapRepository.deleteById(scrap.getId());
  }

  @Override
  public Page<ScrapCountResponse> getScrapCount(String campus, Pageable pageable) {
    return scrapRepository.findAllCustom(campus, pageable).map(ScrapCountResponse::toDto);
  }
}
