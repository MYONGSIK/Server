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
  private String code;

  @NotNull @NotBlank
  @ApiModelProperty(required = true, dataType = "String")
  private String name;

  @NotNull @NotBlank
  @ApiModelProperty(required = true, dataType = "Long")
  private Long distance;

  @NotNull @NotBlank
  @ApiModelProperty(required = true, dataType = "String")
  private String category;

  @NotNull @NotBlank
  @ApiModelProperty(required = true, dataType = "String")
  private String address;

  @NotNull @NotBlank
  @ApiModelProperty(required = true, dataType = "String")
  private String contact;

  @NotNull @NotBlank
  @ApiModelProperty(required = true, dataType = "String")
  private String urlAddress;

  @NotNull @NotBlank
  @ApiModelProperty(required = true, dataType = "String")
  private String phoneId;

  @NotNull @NotBlank
  @ApiModelProperty(required = true, dataType = "String")
  private String campus;
}
