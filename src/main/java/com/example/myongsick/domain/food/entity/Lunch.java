package com.example.myongsick.domain.food.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@RequiredArgsConstructor
@Getter
public class Lunch {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate toDay;

    private String status; //운영 미운영
    private String type;
    private String lunch1;
    private String lunch2;
    private String lunch3;
    private String lunch4;
    private String lunch5;
    private String lunch6;

    private Long love;
    private Long hate;

    @ManyToOne
    private Week week;

    @Builder
    public Lunch(LocalDate toDay, String type, String status, String lunch1, String lunch2, String lunch3, String lunch4, String lunch5, String lunch6, Week week) {
        this.toDay = toDay;
        this.type = type;
        this.status = status;
        this.lunch1 = lunch1;
        this.lunch2 = lunch2;
        this.lunch3 = lunch3;
        this.lunch4 = lunch4;
        this.lunch5 = lunch5;
        this.lunch6 = lunch6;
        this.love = 0L;
        this.hate = 0L;
        this.week = week;
    }

    public void mindReflection(String mind, String calculation) {
        if(mind.equals("love")){//좋아요
            if(calculation.equals("plus")){//플러스
                this.love++;
            }else{//마이너스
                this.love--;
            }
        }else{//싫어요
            if(calculation.equals("plus")){//플러스
                this.hate++;
            }else{//마이너스
                this.hate--;
            }
        }
    }
}
