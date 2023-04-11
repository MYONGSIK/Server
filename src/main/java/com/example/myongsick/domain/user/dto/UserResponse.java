package com.example.myongsick.domain.user.dto;

import com.example.myongsick.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
  private Long id;
  private String phoneId;

  public static UserResponse toDto(User user) {
    return UserResponse.builder()
        .id(user.getId())
        .phoneId(user.getPhoneId())
        .build();
  }
}
