package com.ll.exam.sbb;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity // 아래 Question 클래스는 엔티티 클래스이다.
// 아래 클래스와 1:1로 매칭되는 테이블이 DB에 없다면, 자동으로 생성되어야 한다.
public class Question {
    @Id // primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Integer id;

    @Column(length = 200) // varchar(200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    //이 컬럼은 생기지않음. 자바쪽에서 편하려고 달아놓음. 지워도 상관없음. answer 어디에 적혀있는지 알려주는 것.
    //remove 걸어놔서 question 삭제되면 answer도 같이 삭제됨.
    @OneToMany(mappedBy = "question", cascade = {CascadeType.ALL})
    // 원래는 fetch타입이 lazy인데 EAGER로 변경
    private List<Answer> answerList = new ArrayList<>();

    public void addAnswer(Answer answer) {
        answer.setQuestion(this);
        getAnswerList().add(answer);
    }
}