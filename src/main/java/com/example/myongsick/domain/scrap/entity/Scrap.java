package com.example.myongsick.domain.scrap.entity;

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

  private String storeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Builder
  public Scrap(String storeId, User user) {
    this.storeId = storeId;
    this.addUser(user);
  }

  public void addUser(User user){
    this.user = user;
    user.addScrap(this);
  }
}
