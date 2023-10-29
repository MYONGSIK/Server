package com.example.myongsick.domain.mark.entity;

import java.io.Serializable;

public class MarkId implements Serializable {

  private Long user;
  private Long store;

  public MarkId(){}
  public MarkId(Long user, Long store) {
    super();
    this.user = user;
    this.store = store;
  }
}
