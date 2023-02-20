package com.example.myongsick.domain.v2;

import com.example.myongsick.domain.user.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Getter
@Entity
public class Scrap {

  @Id @Column(name = "scrap_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String storeId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id")
  private User user;
}
