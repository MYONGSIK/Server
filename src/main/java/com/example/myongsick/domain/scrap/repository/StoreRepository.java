package com.example.myongsick.domain.scrap.repository;

import com.example.myongsick.domain.scrap.dto.CountResponse;
import com.example.myongsick.domain.scrap.entity.Store;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StoreRepository extends JpaRepository<Store, Long> {
  Optional<Store> findByCode(@Param("code") String code);

  @Query(
      nativeQuery = true,
      value = "select a.id as storeId, a.code, a.name, a.category, a.address, a.contact, a.url_address as urlAddress, CAST(a.distance AS UNSIGNED) as distance, count(a.id) as scrapCount, a.latitude, a.longitude \n"
          + "from store a join scrap b on a.id = b.id \n"
          + "where a.id = :storeId \n"
          + "group by a.id"
  )
  Optional<CountResponse> findByIdCustom(Long storeId);
}
