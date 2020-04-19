package com.example.demo.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.demo.post.CommentSpec.isBest;
import static com.example.demo.post.CommentSpec.isGood;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTest {

    @Autowired
    CommentRepository comments;

    @Autowired
    PostRepository posts;


    @Test
    public void getComment(){
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = posts.save(post);


        Comment comment = new Comment();
        comment.setComment("Spring data jpa");
        comment.setPost(savedPost);
        comment.setUp(10);
        comment.setDown(1);
        comments.save(comment);


        comments.findByPost_Id(1l);
    }

    @Test
    public void spec(){
        Page<Comment> page = comments
                .findAll(isBest().or(isGood()), PageRequest.of(0,10));
    }
}