package com.example.myongsick.global.object;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class ApiErrorResponse {

    private boolean success;
    private LocalDateTime timeStamp;
    private String dayOfTheWeek;
    private String errorCode;
    private List<String> message;

    public ApiErrorResponse(String errorCode, List<String> message) {
        this.success = false;
        this.timeStamp = LocalDateTime.now().withNano(0);
        this.dayOfTheWeek = getDayOfTheWeek(LocalDateTime.now());
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getDayOfTheWeek(LocalDateTime localDateTime){
        String dayOf = "";
        switch (localDateTime.getDayOfWeek()){
            case SUNDAY:
                dayOf="일요일";
                break;
            case MONDAY:
                dayOf="월요일";
                break;
            case TUESDAY:
                dayOf="화요일";
                break;
            case WEDNESDAY:
                dayOf="수요일";
                break;
            case THURSDAY:
                dayOf="목요일";
                break;
            case FRIDAY:
                dayOf="금요일";
                break;
            case SATURDAY:
                dayOf="토요일";
                break;
        }
        return dayOf;
    }
}
