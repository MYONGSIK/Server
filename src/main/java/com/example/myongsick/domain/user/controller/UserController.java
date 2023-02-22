package com.example.myongsick.domain.user.controller;

import com.example.myongsick.domain.user.dto.UserRequest;
import com.example.myongsick.domain.user.dto.UserResponse;
import com.example.myongsick.domain.user.service.UserService;
import com.example.myongsick.global.object.ApplicationResponse;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v2/users")
public class UserController {
  private final UserService userService;

  @PostMapping
  @ApiOperation(value = "유저 등록")
  public ApplicationResponse<UserResponse> createUser(
      @RequestBody @Valid UserRequest request
  ) {
    return ApplicationResponse.ok(userService.createUser(request));
  }
}
