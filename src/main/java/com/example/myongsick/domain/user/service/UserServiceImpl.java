package com.example.myongsick.domain.user.service;

import com.example.myongsick.domain.user.dto.UserRequest;
import com.example.myongsick.domain.user.dto.UserResponse;
import com.example.myongsick.domain.user.entity.User;
import com.example.myongsick.domain.user.exception.AlreadyUserException;
import com.example.myongsick.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

  private final UserRepository userRepository;
  @Override
  @Transactional
  public UserResponse createUser(UserRequest request) {
    if (userRepository.findByPhoneId(request.getPhoneId()).isPresent()) throw new AlreadyUserException();
    User user = userRepository.save(User.builder().phoneId(request.getPhoneId()).build());
    return UserResponse.toDto(user);
  }
}
