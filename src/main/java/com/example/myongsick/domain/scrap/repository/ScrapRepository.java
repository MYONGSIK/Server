package com.example.myongsick.domain.scrap.repository;

import com.example.myongsick.domain.scrap.dto.CountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.domain.scrap.entity.Store;
import com.example.myongsick.domain.user.entity.User;
import com.example.myongsick.domain.scrap.entity.Scrap;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
  Page<Scrap> findAllByUser(User user, Pageable pageable);

  Optional<Scrap> findByStoreAndUser(Store store, User user);
  @Query(nativeQuery = true,
      value = "select a.id as storeId, b.code, b.name, b.category, b.address, b.contact, b.url_address, b.distance, count(a.id) as scrapCount\n"
          + "from scrap a join store b on a.id = b.id\n"
          + "group by a.id",
      countQuery = "select count(id) as scrapCount from scrap "
  )
  Page<CountResponse> findAllCustom(Pageable pageable);
}