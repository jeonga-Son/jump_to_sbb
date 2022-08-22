package com.ll.exam.sbb.question;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


//@AllArgsConstructor 어노테이션은 모든 필드 값을 파라미터로 받는 생성자를 만들어줍니다.
//@AllArgsConstructor
@Setter
@Getter
public class QuestionForm {
    @NotEmpty(message = "제목은 필수항목입니다.")
    @Size(max = 200, message = "제목을 200자 이하로 입력해주세요.")
    private String subject;
    @NotEmpty(message = "내용은 필수항목입니다.")
    private String content;

}