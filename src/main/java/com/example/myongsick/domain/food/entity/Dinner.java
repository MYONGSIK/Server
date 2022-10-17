package com.example.myongsick.domain.food.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@RequiredArgsConstructor
public class Dinner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate toDay;

    private String status; //운영 미운영
    private String dinner1;
    private String dinner2;
    private String dinner3;
    private String dinner4;
    private String dinner5;
    private String dinner6;

    private Long love;
    private Long hate;

    @ManyToOne
    private Week week;

    @Builder
    public Dinner(LocalDate toDay, String status, String dinner1, String dinner2, String dinner3, String dinner4, String dinner5, String dinner6, Week week) {
        this.toDay = toDay;
        this.status = status;
        this.dinner1 = dinner1;
        this.dinner2 = dinner2;
        this.dinner3 = dinner3;
        this.dinner4 = dinner4;
        this.dinner5 = dinner5;
        this.dinner6 = dinner6;
        this.love = 0L;
        this.hate = 0L;
        this.week = week;
    }
}
