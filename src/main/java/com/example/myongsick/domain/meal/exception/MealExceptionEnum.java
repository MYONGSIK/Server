package com.example.myongsick.domain.meal.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum MealExceptionEnum {
    NOT_OPERATED("M0000", HttpStatus.METHOD_NOT_ALLOWED, "금일 학생식당은 운영하지 않습니다."),
    NOT_FOUND_AREA("M0001", HttpStatus.NOT_FOUND,"해당 지역을 찾을 수 없습니다."),
    NOT_FOUND_WEEK("M0002", HttpStatus.NOT_FOUND, "해당하는 주를 찾을 수 없습니다."),
    ALREADY_AREA("M0003", HttpStatus.BAD_REQUEST, "이미 등록된 지역입니다.");

    public final String CODE;
    public final HttpStatus httpStatus;
    public final String MESSAGE;
}
