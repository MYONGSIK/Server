package com.example.myongsick.domain.dish.entity;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@NoArgsConstructor
@Document
public class Hate {
  @Id
  @Field(value = "_id", targetType = FieldType.OBJECT_ID)
  private String _id;

  private String userId;

  private String dishId;

  @Builder
  public Hate(
      String userId,
      String dishId
  ) {
    this.userId = userId;
    this.dishId = dishId;
  }
}
