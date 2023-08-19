package com.example.myongsick.domain.review.entity;

import com.example.myongsick.domain.user.entity.User;
import com.example.myongsick.global.entity.BaseEntity;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

  @Id @Column(name = "review_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String registeredAt;

  private String content;

  private String area;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;


  @Builder
  public Review(
      User user,
      String content,
      String registeredAt,
      String area) {
    this.user = user;
    this.registeredAt = registeredAt;
    this.content = content;
    this.area = area;
    this.addUser(user);
  }

  public void addUser(User user){
    user.addReview(this);
  }
}
