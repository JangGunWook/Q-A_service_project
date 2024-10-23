package com.sbs.exam.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity//아래 Question 크래스는 엔터티 클래스이다
//아래 클래스와 1:1로 매칭되는 테이블이 DB에 없다면, 자동으로 생성되어야 한다.

public class Question {
    @Id //해당 컬럼을 primary key로 사용
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    @Column(name = "id")
    private Integer id;

    @Column(name = "subject", length = 200) //VARCHAR 애의 너비를
    private String subject;

    @Column(length = 1000, nullable = false)   //oracle 에는 TEXT라는 데이터 타입이 없음
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE) //CascadeType.REMOVE : Question에 달려있는 Answer클래스의 answer까지 자동으로 삭제됨
    // 실제 테이블에는 생성되지 않음! DB컬럼에는 정보를 1개만 넣을 수 있기 때문이다
    private List<Answer> answerList;


}
