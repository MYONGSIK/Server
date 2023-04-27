package com.example.myongsick.domain.scrap.repository;

import static com.example.myongsick.domain.scrap.entity.QScrap.scrap;
import static com.example.myongsick.domain.scrap.entity.QStore.store;

import com.example.myongsick.domain.scrap.dto.QScrapCountResponse;
import com.example.myongsick.domain.scrap.dto.ScrapCountResponse;
import com.example.myongsick.domain.scrap.entity.CampusType;
import com.example.myongsick.domain.scrap.entity.Store;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ScrapRepositoryImpl implements ScrapRepositoryCustom{
  private final JPAQueryFactory jpaQueryFactory;

  @Override
  public Page<ScrapCountResponse> findAllByCampusWithPaging(String campus, Pageable pageable) {
    List<ScrapCountResponse> result =  jpaQueryFactory.select(new QScrapCountResponse(store.id.as("storeId"), store.code, store.name, store.category, store.address, store.contact, store.urlAddress, store.distance, store.latitude, store.longitude, store.scrapList.size().as("scrapCount")))
        .from(store)
        .where(store.campus.eq(CampusType.valueOf(campus)))
        .groupBy(store.code)
        .orderBy(getOrderSpecifier(pageable.getSort()).stream().toArray(OrderSpecifier[]::new))
        .offset(pageable.getOffset())
        .limit(pageable.getPageSize())
        .fetch();

    Long count = jpaQueryFactory
        .select(scrap.count())
        .from(scrap)
        .fetchOne();

    return new PageImpl<>(result, pageable, count);
  }

  private List<OrderSpecifier> getOrderSpecifier(Sort sort) {
    List<OrderSpecifier> orderSpecifiers = new ArrayList<>();
    sort.stream()
        .forEach(order -> {
          Order direction = order.isAscending() ? Order.ASC : Order.DESC;
          String property = order.getProperty();
          if(property.equals("scrapCount")) {
            orderSpecifiers.add(new OrderSpecifier(direction, store.scrapList.size()));
          } else if(property.equals("distance")) {
            orderSpecifiers.add(new OrderSpecifier(direction, store.distance.castToNum(Long.class)));
          } else {
            PathBuilder orderByExpression = new PathBuilder(Store.class, "store");
            orderSpecifiers.add(new OrderSpecifier(direction, orderByExpression.get(property)));
          }
          });
    return orderSpecifiers;
  }
}
