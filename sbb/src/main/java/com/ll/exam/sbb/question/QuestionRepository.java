package com.ll.exam.sbb.question;

import com.ll.exam.sbb.base.RepositoryUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long>, RepositoryUtil {

    Question findBySubject(String s);

    Question findBySubjectAndContent(String subject, String content);

    List<Question> findBySubjectLike(String subject);

    @Transactional
    @Modifying
    @Query(value = "ALTER TABLE question AUTO_INCREMENT = 1", nativeQuery = true) //이후에 데이터를 추가했을 때 1번부터 나오도록.
    void truncate(); // 이거 지우면 안됨, truncateTable 하면 자동으로 이게 실행됨

    Page<Question> findBySubjectContains(String kw, Pageable pageable);

    Page<Question> findBySubjectContainsOrContentContains(String kw, String kw_, Pageable pageable);

    Page<Question> findBySubjectContainsOrContentContainsOrAuthor_usernameContainsOrAnswerList_contentContains(String kw, String kw_, String kw__, String kw___, Pageable pageable);

    Page<Question> findDistinctBySubjectContainsOrContentContainsOrAuthor_usernameContainsOrAnswerList_contentContainsOrAnswerList_author_username(String kw, String kw_, String kw__, String kw___, String kw____, Pageable pageable);
}