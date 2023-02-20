package com.example.myongsick.domain.user;

import com.example.myongsick.global.object.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
public class UserController {
  private final UserRepository userRepository;

  @PostMapping
  public ApplicationResponse<User> createUser(
      @RequestBody UserRequest request
  ) {
    User user = User.builder().phoneId(request.getPhoneId()).build();
    return ApplicationResponse.ok(userRepository.save(user));
  }
}
