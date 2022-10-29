package com.example.myongsick.global.init;

import com.example.myongsick.domain.food.dto.request.LunchAddRequest;
import com.example.myongsick.domain.food.service.FoodService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class initDB {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddlConfig;

    private final FoodService foodService;

    @PostConstruct
    public void init(){
//        if (ddlConfig.equals("create")){
//            foodService.addWeek("2022-10-02","2022-10-08");
//            foodService.addWeek("2022-10-09","2022-10-15");
//            foodService.addWeek("2022-10-16","2022-10-22");
//            foodService.addWeek("2022-10-23","2022-10-29");
//            foodService.addWeek("2022-10-30","2022-11-05");
//            foodService.addWeek("2022-11-06","2022-11-12");
//            foodService.addWeek("2022-11-13","2022-11-19");
//        }
    }

}
