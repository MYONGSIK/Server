package com.example.myongsick.domain.mark.entity;

import com.example.myongsick.domain.scrap.entity.Store;
import com.example.myongsick.domain.user.entity.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "mark")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(MarkId.class)
public class StoreMark implements Serializable {

  @Id
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Id
  @ManyToOne
  @JoinColumn(name = "store_id")
  private Store store;

  public StoreMark StoreMark(
      User user,
      Store store
  ) {
    this.user = user;
    this.store = store;
    return this;
  }
}
