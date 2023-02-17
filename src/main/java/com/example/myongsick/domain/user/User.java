package com.example.myongsick.domain.user;

import com.example.myongsick.domain.review.entity.Review;
import com.example.myongsick.domain.v2.Scrap;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String phoneId;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  List<Scrap> scrapList = new ArrayList<>();


  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  List<Review> reviewList = new ArrayList<>();

  @Builder
  public User(String phoneId) {
    this.phoneId = phoneId;
  }
  public void addReview(Review review) {
    this.reviewList.add(review);
  }
}
