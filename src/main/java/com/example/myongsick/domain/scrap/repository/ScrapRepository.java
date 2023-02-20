package com.example.myongsick.domain.scrap.repository;

import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.domain.user.User;
import com.example.myongsick.domain.v2.Scrap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {

  @Query(nativeQuery = true,
      value = "select * from ("
          + "select a.scrapId, a.storeId from scrap a join fetch a.user u where u.id =:user.id union"
          + "select count(s.storeId) as scrapCnt from scrap s"
          + ")",
      countQuery = "select * from scrap s join fetch s.user u where u.id =:user.id"
  )
  Page<ScrapResponse> findAllByUserCustom(User user, Pageable pageable);
  Page<Scrap> findAllByUser(User user, Pageable pageable);
}