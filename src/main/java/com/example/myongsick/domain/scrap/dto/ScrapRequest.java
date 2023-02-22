package com.example.myongsick.domain.scrap.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@ApiModel(description = "찜꽁 리스트에 가게 등록")
public class ScrapRequest {

  @NotNull @NotBlank
  @ApiModelProperty(required = true, dataType = "String")
  private String storeId;

  @NotNull @NotBlank
  @ApiModelProperty(required = true, dataType = "String")
  private String phoneId;
}
