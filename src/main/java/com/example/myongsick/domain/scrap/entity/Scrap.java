package com.example.myongsick.domain.scrap.entity;

import static javax.persistence.FetchType.LAZY;

import com.example.myongsick.domain.food.entity.Week;
import com.example.myongsick.domain.user.entity.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scrap {

  @Id @Column(name = "scrap_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "id")
  private Store store;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Builder
  public Scrap(Store store, User user) {
    this.addStore(store);
    this.addUser(user);
  }

  public void addUser(User user){
    this.user = user;
    user.addScrap(this);
  }

  public void addStore(Store store) {
    this.store = store;
    store.addScrap(this);
  }
}
