package com.example.myongsick.global.exception;

import com.example.myongsick.global.object.ApiErrorResponse;
import com.example.myongsick.global.object.ApplicationErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String LOG_FORMAT ="Class : {}, CODE : {}, Message : {}";
    private static final String INTERNAL_SERVER_ERROR_CODE = "S0001";


    @ExceptionHandler(ApplicationException.class)
    public ApplicationErrorResponse<Void> applicationException(ApplicationException e) {
        String errorCode = e.getErrorCode();

        log.warn(
                LOG_FORMAT,
                e.getClass().getSimpleName(),
                errorCode,
                e.getMessage()
        );
        return ApplicationErrorResponse.error(e);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorResponse> runtimeException(RuntimeException e) {
        log.error(
                LOG_FORMAT,
                e.getClass().getSimpleName(),
                INTERNAL_SERVER_ERROR_CODE,
                e.getMessage()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiErrorResponse(INTERNAL_SERVER_ERROR_CODE, Arrays.asList("런타임 에러가 발생했습니다.")));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApplicationErrorResponse<ApiErrorResponse> validationException(MethodArgumentNotValidException e){
        log.error(
                LOG_FORMAT,
                e.getClass().getSimpleName(),
                INTERNAL_SERVER_ERROR_CODE,
                e.getMessage()
        );
        return ApplicationErrorResponse.error(
                e.getBindingResult().getAllErrors().get(0).getDefaultMessage()
        );
    }
}
