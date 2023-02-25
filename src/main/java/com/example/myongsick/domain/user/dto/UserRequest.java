package com.example.myongsick.domain.user.dto;

import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
//  @ApiModelProperty(required = true, dataType = "String")
  @NotNull
  @NotBlank
  private String phoneId;
}
