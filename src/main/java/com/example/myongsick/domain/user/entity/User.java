package com.example.myongsick.domain.user.entity;

import com.example.myongsick.domain.mark.entity.StoreMark;
import com.example.myongsick.domain.review.entity.Review;
import com.example.myongsick.domain.scrap.entity.Scrap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private Long id;

  private String phoneId;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  List<Scrap> scrapList = new ArrayList<>();


  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  List<Review> reviewList = new ArrayList<>();

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  List<StoreMark> storeMarkList = new ArrayList<>();

  @Builder
  public User(String phoneId) {
    this.phoneId = phoneId;
  }
  public void addReview(Review review) {
    this.reviewList.add(review);
  }

  public void addScrap(Scrap scrap) {
    this.scrapList.add(scrap);
  }
}
