//package com.example.myongsick.domain.dish.entity;
//
//import com.example.myongsick.domain.scrap.entity.CampusType;
//
//import org.springframework.data.annotation.Id;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//import org.springframework.data.mongodb.core.mapping.FieldType;
//
//@Getter
//@NoArgsConstructor
//@Document
//public class Restaurant {
//
//  @Id
//  @Field(value = "_id", targetType = FieldType.OBJECT_ID)
//  private String _id;
//
//  @Field("name")
//  private String name;
//
//  private CampusType campusType;
//
//  @Builder
//  public Restaurant(
//      String name,
//      String campusType
//  ) {
//    this.name = name;
//    this.campusType = CampusType.valueOf(campusType);
//  }
//}
