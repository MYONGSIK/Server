package com.example.myongsick.domain.v2;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Getter
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String phoneId;

  @OneToMany
  List<Scrap> scrapList = new ArrayList<>();

  @OneToMany
  List<Review> reviewList = new ArrayList<>();
}
