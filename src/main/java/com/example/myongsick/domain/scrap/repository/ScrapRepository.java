package com.example.myongsick.domain.scrap.repository;

import com.example.myongsick.domain.scrap.dto.CountResponse;
import com.example.myongsick.domain.scrap.entity.Scrap;
import com.example.myongsick.domain.scrap.entity.Store;
import com.example.myongsick.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ScrapRepository extends JpaRepository<Scrap, Long> {
  Page<Scrap> findAllByUser(User user, Pageable pageable);

  Optional<Scrap> findByStoreAndUser(Store store, User user);
  @Query(nativeQuery = true,
      value = "select a.id as storeId, a.code, a.name, a.category, a.address, a.contact, a.url_address as urlAddress, CAST(a.distance AS UNSIGNED) as distance, count(a.id) as scrapCount, a.latitude, a.longitude \n"
          + "from store a join scrap b on a.id = b.id \n"
          + "where a.campus = :campus \n"
          + "group by a.id",
      countQuery = "select count(id) as scrapCount from scrap "
  )
  Page<CountResponse> findAllCustom(@Param("campus") String campus, Pageable pageable);
}