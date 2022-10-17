package com.example.myongsick.global.config;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

@Component
public class TimeZone {

    @PostConstruct
    public void started(){
        java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("Asia/Seoul"));
    }
}
