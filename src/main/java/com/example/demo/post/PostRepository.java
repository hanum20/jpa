package com.example.demo.post;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    // 자동완성 어케?
    List<Post> findByTitleStartsWith(String title);


    // 정렬 옵션은 반드시 property or alias가 와야만 한다.
    @Query("SELECT p FROM #{#entityName} AS p WHERE p.title = :title")
    List<Post> findByTitle(@Param("title") String keyword, Sort sort);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("UPDATE Post p Set p.title = ?1 WHERE p.id = ?2")
    int updateTitle(String title, Long id);
}
