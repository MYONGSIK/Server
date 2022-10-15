package com.example.myongsick.domain.food.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate toDay;

    private String classification; //중식 석식
    private String status; //운영 미운영
    private String food1;
    private String food2;
    private String food3;
    private String food4;
    private String food5;
    private String food6;

    private Long love;
    private Long hate;

    @ManyToOne
    private Week week;

    @Builder
    public Food(LocalDate toDay, String classification, String status, String food1, String food2, String food3, String food4, String food5, String food6, Week week) {
        this.toDay = toDay;
        this.classification = classification;
        this.status = status;
        this.food1 = food1;
        this.food2 = food2;
        this.food3 = food3;
        this.food4 = food4;
        this.food5 = food5;
        this.food6 = food6;
        this.hate = 0L;
        this.love = 0L;
        this.week =  week;
    }
}
