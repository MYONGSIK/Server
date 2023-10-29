package com.example.myongsick.domain.scrap.entity;

import com.example.myongsick.domain.mark.entity.StoreMark;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
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
  private String distance;
  private String category;
  private String address;
  private String contact;
  private String urlAddress;
  @Enumerated(EnumType.STRING)
  private CampusType campus;

  private String latitude;
  private String longitude;

  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
  List<Scrap> scrapList = new ArrayList<>();

  @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
  List<StoreMark> storeMarkList = new ArrayList<>();

  @Builder
  public Store(
      String code,
      String name,
      String distance,
      String category,
      String address,
      String contact,
      String urlAddress,
      CampusType campus,
      String latitude,
      String longitude) {
    this.code = code;
    this.name = name;
    this.category = category;
    this.distance = distance;
    this.address = address;
    this.contact = contact;
    this.urlAddress = urlAddress;
    this.campus = campus;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public void addScrap(Scrap scrap) {
    this.scrapList.add(scrap);
  }

  public void updatePoint(String x, String y) {
    this.latitude = x;
    this.longitude = y;
  }
}
