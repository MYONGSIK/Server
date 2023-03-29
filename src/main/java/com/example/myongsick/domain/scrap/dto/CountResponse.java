package com.example.myongsick.domain.scrap.dto;

public interface CountResponse {
  Long getStoreId();
  String getCode();
  String getName();
  String getCategory();
  String getAddress();
  String getContact();
  String getUrlAddress();
  Long getDistance();
  int getScrapCount();
}
