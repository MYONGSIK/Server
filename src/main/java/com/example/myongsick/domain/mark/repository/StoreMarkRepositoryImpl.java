package com.example.myongsick.domain.mark.repository;

import static com.example.myongsick.domain.mark.entity.QStoreMark.storeMark;
import static com.example.myongsick.domain.scrap.entity.QStore.store;
import static com.example.myongsick.domain.user.entity.QUser.user;

import com.example.myongsick.domain.mark.entity.StoreMark;
import com.example.myongsick.domain.scrap.dto.QScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.QScrapResponse;
import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapResponse;
import com.example.myongsick.domain.scrap.entity.CampusType;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class StoreMarkRepositoryImpl implements StoreMarkRepositoryCustom {

  private final JPAQueryFactory jpaQueryFactory;
  /**
   * =============================================================================================
   *  유저가 가게를 찜꽁했는지 조회 -- 1건 조회
   * =============================================================================================
   * */
  @Override
  public StoreMark findByUserIdAndStoreId(Long userId, Long storeId) {
    return jpaQueryFactory
        .select(storeMark)
        .from(storeMark)
        .where(
            storeMark.user.id.eq(userId),
            storeMark.store.id.eq(storeId))
        .fetchOne();
  }
  /**
   * =============================================================================================
   *  캠퍼스별 가게 목록 조회 -- 스크랩 수 포함
   * =============================================================================================
   * */
  @Override
  public Page<ScrapCountResponse> findByCampus(String campus, Pageable pageable, String orderBy) {
    var result = jpaQueryFactory
        .select(new QScrapCountResponse(
            store.id.as("storeId"),
            store.code,
            store.name,
            store.category,
            store.address,
            store.contact,
            store.urlAddress,
            store.distance,
            store.latitude,
            store.longitude,
            store.storeMarkList.size().intValue().as("scrapCount")
        ))
        .from(store)
        .where(store.campus.eq(CampusType.valueOf(campus)))
        .orderBy(getOrderSpecifier(orderBy))
        .fetch();
    var countQuery = jpaQueryFactory
        .select(store.storeMarkList.size())
        .from(store)
        .where(store.campus.eq(CampusType.valueOf(campus)))
        .fetch().size();
    return new PageImpl<>(result, pageable, countQuery);
  }
  /**
   * =============================================================================================
   *  유저의 찜꽁리스트 조회
   * =============================================================================================
   * */
  @Override
  public Page<ScrapResponse> findByUserId(Long userId, Pageable pageable) {
    var result = jpaQueryFactory
        .select(new QScrapResponse(
            store.id,
            store.code,
            store.name,
            store.category,
            store.address,
            store.urlAddress,
            store.distance,
            store.latitude,
            store.longitude
        ))
        .from(storeMark)
        .leftJoin(storeMark.store, store)
        .leftJoin(storeMark.user, user)
        .where(user.id.eq(userId))
        .groupBy(storeMark.store, storeMark.user)
        .fetch();
    var countQuery = jpaQueryFactory
        .select(storeMark.count())
        .from(storeMark)
        .leftJoin(storeMark.store, store)
        .leftJoin(storeMark.user, user)
        .where(user.id.eq(userId))
        .groupBy(storeMark.store, storeMark.user)
        .fetch().size();
    System.out.println(countQuery);
    return new PageImpl<>(result, pageable, countQuery);
  }
  /**
   * =============================================================================================
   *  PRIVATE FUNCTION
   * =============================================================================================
   * */
  private OrderSpecifier getOrderSpecifier(String orderBy) {
    if (orderBy.equals("distance")) {
      return new OrderSpecifier(Order.DESC, store.distance.castToNum(Long.class));
    } else {
      return new OrderSpecifier(Order.DESC, store.storeMarkList.size());
    }
  }
}
