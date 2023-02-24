package com.example.myongsick.domain.review.entity;

import com.example.myongsick.domain.meal.entity.Meal;
import com.example.myongsick.domain.user.entity.User;
import com.example.myongsick.global.entity.BaseEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

  private String content;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "meal_id")
  private Meal meal;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Builder
  public Review(User user, String content, Meal meal) {
    this.user = user;
    this.content = content;
    this.meal = meal;
    this.addUser(user);
  }

  public void addUser(User user){
    user.addReview(this);
  }
}
