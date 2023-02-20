package com.example.myongsick.global.object;

import com.example.myongsick.global.exception.ApplicationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationErrorResponse<T>{
    private boolean success;
    private int httpCode;
    private String errorCode;
    private LocalDateTime localDateTime;
    private String dayOfTheWeek;
    private HttpStatus httpStatus;
    private String message;

    public static <T> ApplicationErrorResponse<T> error(ApplicationException e){
        return (ApplicationErrorResponse<T>) ApplicationErrorResponse.builder()
                .success(false)
                .httpCode(e.getHttpStatus().value())
                .errorCode(e.getErrorCode())
                .localDateTime(LocalDateTime.now())
                .dayOfTheWeek(getDayOfTheWeek(LocalDateTime.now()))
                .httpStatus(e.getHttpStatus())
                .message(e.getMessage())
                .build();
    }

    public static <T> ApplicationErrorResponse<T> error(String message){
        return (ApplicationErrorResponse<T>) ApplicationErrorResponse.builder()
                .success(false)
                .httpCode(404)
                .errorCode("V0001")
                .localDateTime(LocalDateTime.now())
                .httpStatus(HttpStatus.HTTP_VERSION_NOT_SUPPORTED)
                .message(message)
                .build();
    }

    public static String getDayOfTheWeek(LocalDateTime localDateTime){
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
