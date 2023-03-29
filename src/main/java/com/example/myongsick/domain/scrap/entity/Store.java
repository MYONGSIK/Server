package com.example.myongsick.domain.scrap.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
public class Store {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String code;
  private String name;
  private Long distance;
  private String category;
  private String address;
  private String contact;
  private String urlAddress;
  @Enumerated(EnumType.STRING)
  private CampusType campus;

  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
  List<Scrap> scrapList = new ArrayList<>();

  @Builder
  public Store(
      String code,
      String name,
      Long distance,
      String category,
      String address,
      String contact,
      String urlAddress,
      CampusType campus) {
    this.code = code;
    this.name = name;
    this.category = category;
    this.distance = distance;
    this.address = address;
    this.contact = contact;
    this.urlAddress = urlAddress;
    this.campus = campus;
  }

  public void addScrap(Scrap scrap) {
    this.scrapList.add(scrap);
  }
}
