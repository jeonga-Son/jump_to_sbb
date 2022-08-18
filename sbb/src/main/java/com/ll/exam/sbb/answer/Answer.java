package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.question.Question;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    @ManyToOne //이거를 붙여야 됨. 이거때문에 ForeignKey 만들어짐.
    private Question question; //question에 참조값이 들어감
}