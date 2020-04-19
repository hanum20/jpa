package com.example.demo.post;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long>, JpaSpecificationExecutor<Comment> {

    @EntityGraph(attributePaths = "post")
    Optional<Comment> getById(Long id);

    @Transactional(readOnly = true)
    List<CommentSummary> findByPost_Id(Long id);

}
