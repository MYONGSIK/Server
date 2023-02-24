package com.example.myongsick.global.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public class BaseEntity implements Serializable {
  @CreatedDate
  @Column(updatable = false)
  private LocalDate createdAt;

  @LastModifiedDate
  private LocalDate updatedAt;

  @PrePersist
  public void prePersist(){
    LocalDate now = LocalDate.now();
    createdAt = now;
    updatedAt = now;
  }

  @PreUpdate
  public void preUpdate(){
    updatedAt = LocalDate.now();
  }

}
