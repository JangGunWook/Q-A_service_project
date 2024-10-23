package com.sbs.exam.sbb;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")  //ORACLE에서는  컬럼이름을
    private Integer id;

    //@Column(columnDefinition = "TEXT")//oracle 에는 TEXT라는 데이터 타입이 없음
    @Column(length = 1000, nullable = false)
    private String content;

    private LocalDateTime createDate;

    // 관계 설정 추가
    @ManyToOne
    @JoinColumn(name = "question_id")  // 외래키 이름 설정
    //Question class의 id값에 외래키 설정
    private Question question;  //해당 질문을 참조한다!
}