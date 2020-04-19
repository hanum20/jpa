package com.example.demo.post;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/*
* @CreatedDate
* @CreatedBy
* @LastModifiedDate
* @LastModifiedBy
* 는 Auditing 설정을 해주어야 동작한다.
* */
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Comment {

    @Id @GeneratedValue
    private Long id;

    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;

    private int up;

    private  int down;

    private boolean best;

    @CreatedDate
    private Date created;

    // Mapping을 시켜줘야 동작한다.
    @CreatedBy
    @ManyToOne
    private Account createdBy;

    @LastModifiedDate
    private Date updated;

    @LastModifiedBy
    @ManyToOne
    private Account updatedBy;

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
        this.up = up;
    }

    public int getDown() {
        return down;
    }

    public void setDown(int down) {
        this.down = down;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
