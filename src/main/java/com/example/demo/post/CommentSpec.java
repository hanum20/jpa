package com.example.demo.post;

import org.springframework.data.jpa.domain.Specification;

public class CommentSpec {

    public static Specification<Comment> isBest() {
        return (Specification<Comment>) (root, query, builder) -> builder.isTrue(root.get(Comment_.BEST));
    }

    public static Specification<Comment> isGood() {
        return (Specification<Comment>) (root, query, builder) -> builder.greaterThan(root.get(Comment_.UP), 10);
    }

}
