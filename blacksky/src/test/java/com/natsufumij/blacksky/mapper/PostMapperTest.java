package com.natsufumij.blacksky.mapper;

import com.natsufumij.blacksky.domain.Post;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author Administrator
 */
@SpringBootTest
public class PostMapperTest {

    @Autowired
    private PostMapper postMapper;

    @Test
    public void testFetch() {
        //Test Correct
        Post post = postMapper.fetchPost(100L);
        Assertions.assertNotNull(post);
        Assertions.assertEquals("我不知道你在说什么", post.getContent());

        //Test Fail
        Assertions.assertNull(postMapper.fetchPost(10L));

        //Test Correct
        List<Post> list = postMapper.fetchPostsByUserId(101L);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());

        //Test Fail
        List<Post> emptyList = postMapper.fetchPostsByUserId(10L);
        Assertions.assertNotNull(emptyList);
        Assertions.assertEquals(0, emptyList.size());
    }

    @Test
    public void testFetchRepliedOn() {
        //Test Correct
        Post p = postMapper.fetchPost(105L);
        Assertions.assertNotNull(p);
        Assertions.assertEquals(2, p.getReplyCount());

        List<Post> list = postMapper.fetchPostsReplyOn(105L);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());

        //Test Fail
        list = postMapper.fetchPostsReplyOn(106L);
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void testPost() {
        //Test Correct
        Post p = new Post();
        p.setUserId(100L);
        p.setContent("我很想知道你是谁");
        postMapper.post(p);

        Assertions.assertNotNull(p.getId());

        Post fetch = postMapper.fetchPost(p.getId());
        Assertions.assertNotNull(fetch);
        Assertions.assertEquals("我很想知道你是谁", fetch.getContent());

        postMapper.deletePost(p.getId());
    }
}
