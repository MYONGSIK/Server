package com.example.myongsick;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class MyongsickApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyongsickApplication.class, args);
    }

}
