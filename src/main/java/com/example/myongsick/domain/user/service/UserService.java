package com.example.myongsick.domain.user.service;

import com.example.myongsick.domain.user.dto.UserRequest;
import com.example.myongsick.domain.user.dto.UserResponse;

public interface UserService {

  UserResponse createUser(UserRequest request);
}
