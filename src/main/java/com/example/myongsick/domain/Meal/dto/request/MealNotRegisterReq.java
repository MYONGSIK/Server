package com.example.myongsick.domain.Meal.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class MealNotRegisterReq {
    private String area;
    private LocalDate startedAt;
    private LocalDate endedAt;
}
