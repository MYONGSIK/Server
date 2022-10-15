package com.example.myongsick.domain.food.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum FoodExceptionEnum {
    NOT_OPERATED("F0000", HttpStatus.METHOD_NOT_ALLOWED, "공휴일은 식당을 운영하지 않습니다.");

    public final String CODE;
    public final HttpStatus httpStatus;
    public final String MESSAGE;
}
