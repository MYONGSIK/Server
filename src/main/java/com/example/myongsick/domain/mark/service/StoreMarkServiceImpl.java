package com.example.myongsick.domain.mark.service;

import com.example.myongsick.domain.mark.entity.StoreMark;
import com.example.myongsick.domain.mark.repository.StoreMarkRepository;
import com.example.myongsick.domain.mark.repository.StoreMarkRepositoryCustom;
import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.domain.scrap.entity.Store;
import com.example.myongsick.domain.scrap.exception.NotFoundStoreException;
import com.example.myongsick.domain.scrap.repository.StoreRepository;
import com.example.myongsick.domain.user.entity.User;
import com.example.myongsick.domain.user.exception.NotFoundUserException;
import com.example.myongsick.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreMarkServiceImpl implements StoreMarkService {
  /**
   * =============================================================================================
   *  DI for Repository
   * =============================================================================================
   * */
  private final UserRepository userRepository;
  private final StoreRepository storeRepository;
  private final StoreMarkRepository storeMarkRepository;
  private final StoreMarkRepositoryCustom storeMarkRepositoryCustom;

  /**
   * =============================================================================================
   *  유저의 찜꽁리스트 조회
   * =============================================================================================
   * */
  @Override
  public Page<ScrapResponse> getMarkList(String phoneId, Pageable pageable) {
    User user = userRepository.findByPhoneId(phoneId).orElseThrow(NotFoundUserException::new);
    return storeMarkRepositoryCustom.findByUserId(user.getId(), pageable);
  }
  /**
   * =============================================================================================
   *  찜꽁리스트에 가게 추가
   * =============================================================================================
   * */
  @Override
  @Transactional
  public void createMark(String phoneId, String storeCode) {
    User user = userRepository.findByPhoneId(phoneId).orElseThrow(NotFoundUserException::new);
    Store store = storeRepository.findByCode(storeCode).orElseThrow(NotFoundStoreException::new);
    var mark = storeMarkRepositoryCustom.findByUserIdAndStoreId(user.getId(), store.getId());
    if (mark == null) {
      mark = StoreMark.builder().user(user).store(store).build();
    }
    storeMarkRepository.save(mark);
  }
  /**
   * =============================================================================================
   *  찜꽁 리스트에서 가게 제거
   * =============================================================================================
   * */
  @Override
  @Transactional
  public void deleteMark(String phoneId, String storeCode) {
    User user = userRepository.findByPhoneId(phoneId).orElseThrow(NotFoundUserException::new);
    Store store = storeRepository.findByCode(storeCode).orElseThrow(NotFoundStoreException::new);
    var mark = storeMarkRepositoryCustom.findByUserIdAndStoreId(user.getId(), store.getId());
    if (mark == null) {
      return;
    }
    storeMarkRepository.delete(mark);
  }
  /**
   * =============================================================================================
   *  찜꽁 수 포함 가게 리스트 조회 -- 스크랩수(DESC), 거리(ASC) 적용
   * =============================================================================================
   * */
  @Override
  public Page<ScrapCountResponse> getMarkCount(String campus, Pageable pageable, String orderBy) {
    return storeMarkRepositoryCustom.findByCampus(campus, pageable, orderBy);
  }
}
