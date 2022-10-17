package com.example.myongsick.domain.food.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@RequiredArgsConstructor
public class Dinner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
