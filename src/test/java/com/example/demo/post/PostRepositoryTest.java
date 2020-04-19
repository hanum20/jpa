package com.example.demo.post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    public void save() {
        Post post = new Post();
        post.setTitle("jpa");
        Post savedPost = postRepository.save(post);

        assertThat(entityManager.contains(post)).isTrue();
        assertThat(entityManager.contains(savedPost)).isTrue();
        assertThat(savedPost == post);

        Post postUpdate = new Post();
        postUpdate.setId(post.getId()); // Id가 있기때문에, merge가 동작
        postUpdate.setTitle("hibernate"); // jpa가 아니라 hibernate가 동작
        Post updatedPost = postRepository.save(postUpdate); // 이 파라메터의 복사본을 persistent로 한다.

        assertThat(entityManager.contains(post)).isTrue();
        assertThat(entityManager.contains(updatedPost)).isTrue();
        assertThat(postUpdate == updatedPost);


        /*
        * 동작하지 않는다.
        * postUpdate는 ID가 있으므로 merge가 동작한다.
        * 따라서 persistent 상태가 되는 것은, postUpdate가 복사되어 persistent 상태가 된
        * updatedPost이다.
        *
        * 따라서, 무조건 save()로 리턴받은 값을 사용할 것
        * */
        postUpdate.setTitle("kzkzkzkzk");

        // 동작한다.
        updatedPost.setTitle("hahaha");

        List<Post> all = postRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void findByTitleStartsWith(){
        Post post = new Post();
        post.setTitle("Spring Data Jpa");
        Post savedPost = postRepository.save(post);

        List<Post> all = postRepository.findByTitleStartsWith("Spring");
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void findByTitle(){
        Post post = new Post();
        post.setTitle("Spring");
        postRepository.save(post);

        // property
        List<Post> all = postRepository.findByTitle("Spring", Sort.by("title"));
        //List<Post> all = postRepository.findByTitle("Spring");
        assertThat(all.size()).isEqualTo(1);
    }

    @Test
    public void updateTitle(){
        Post spring = new Post();
        spring.setTitle("Spring");
        postRepository.save(spring);

        spring.setTitle("hibernte");

        // findAll은 실행 전, 캐시의 싱크를 맞춘다. 이것을 이용하면, update 쿼리를 날릴 수 있다.
        List<Post> all = postRepository.findAll();
    }
}