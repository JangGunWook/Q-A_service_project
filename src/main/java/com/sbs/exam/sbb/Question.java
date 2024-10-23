package com.sbs.exam.sbb;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity//아래 Question 크래스는 엔터티 클래스이다
//아래 클래스와 1:1로 매칭되는 테이블이 DB에 없다면, 자동으로 생성되어야 한다.

public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 200) //VARCHAR 애의 너비를
    private String title;

    @Column(length = 1000, nullable = false)
    private String content;

    private LocalDateTime crateDate;

}
