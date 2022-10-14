package com.example.myongsick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:application-secret.yml")
public class MyongsickApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyongsickApplication.class, args);
    }

}
