package com.ll.exam.sbb.answer;

import com.ll.exam.sbb.question.Question;
import com.ll.exam.sbb.user.SiteUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

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

    private LocalDateTime modifyDate;

    @ManyToOne //이거를 붙여야 됨. 이거때문에 ForeignKey 만들어짐.
    private Question question; //question에 참조값이 들어감

    @ManyToOne
    private SiteUser author;

    //List를 사용하면 한번은 괜찮은데 계속 정보가 들어감?
    @ManyToMany
    Set<SiteUser> voter;
}