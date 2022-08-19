package com.ll.exam.sbb.user;

import lombok.Getter;

// enum 사용 이유
// 객체를 싱글톤으로 쓰고싶다 + 객체의 개수가 무조건 정해져있다.
// enum만 특별하게 출력하면 변수의 이름이 출력됨. ex. ADMIN
// 입력값을 변수처리한 것.
@Getter
public enum UserRole {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}