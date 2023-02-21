package com.example.myongsick.domain.scrap.repository;

import com.example.myongsick.domain.scrap.dto.CountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.domain.user.entity.User;
import com.example.myongsick.domain.scrap.entity.Scrap;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
  Page<Scrap> findAllByUser(User user, Pageable pageable);

  Optional<Scrap> findByStoreId(String storeId);
  @Query(nativeQuery = true,
      value = "select store_id as storeId, count(store_id) as scrapCount from scrap group by store_id",
      countQuery = "select count(store_id) as scrapCount from scrap "
  )
  Page<CountResponse> findAllCustom(Pageable pageable);
}