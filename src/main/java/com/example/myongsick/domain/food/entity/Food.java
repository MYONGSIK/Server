package com.example.myongsick.domain.food.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@RequiredArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime toDay;

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
}
