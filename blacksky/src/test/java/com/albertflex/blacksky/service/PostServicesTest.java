/*
 */
package com.albertflex.blacksky.service;

import com.albertflex.blacksky.domain.Post;

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
public class PostServicesTest {

    @Autowired
    private PostServices postServices;

    @Test
    public void testFetch() {

        //Test Correct
        Post post = postServices.fetchPost(100L);
        Assertions.assertNotNull(post);
        Assertions.assertEquals("我不知道你在说什么", post.getContent());

        //Test Fail
        Assertions.assertNull(postServices.fetchPost(10L).getId());

        //Test Correct
        List<Post> list = postServices.fetchPostsByUserId(101L);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());

        //Test Fail
        List<Post> emptyList = postServices.fetchPostsByUserId(10L);
        Assertions.assertNotNull(emptyList);
        Assertions.assertEquals(0, emptyList.size());
    }

    @Test
    public void testFetchRepliedOn() {
        //Test Correct
        Post p = postServices.fetchPost(105L);
        Assertions.assertNotNull(p);
        Assertions.assertEquals(2, p.getReplyCount());

        List<Post> list = postServices.fetchPostsReplyOn(105L);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(2, list.size());

        //Test Fail
        list = postServices.fetchPostsReplyOn(106L);
        Assertions.assertNotNull(list);
        Assertions.assertTrue(list.isEmpty());
    }

    @Test
    public void testPost() {
        Post p = postServices.post(100L, "我很想知道你是谁");
        Assertions.assertNotNull(p.getId());

        Post fetch = postServices.fetchPost(p.getId());
        Assertions.assertNotNull(fetch);
        Assertions.assertEquals("我很想知道你是谁", fetch.getContent());
        
        postServices.cancel(fetch.getId());
    }
    
    @Test
    public void testReply(){
        
        //Test Correct
        Post dest = postServices.reply(100L,"Nice" ,105L);
        
        Post p = postServices.fetchPost(105L);
        Assertions.assertNotNull(p);
        Assertions.assertEquals(3L, p.getReplyCount());
        Assertions.assertEquals(105L, dest.getReplyPostId());
        
        postServices.cancel(dest.getId());
        p=postServices.fetchPost(105L);
        Assertions.assertEquals(2L, p.getReplyCount());
        p=postServices.fetchPost(dest.getId());
        Assertions.assertNull(p.getId());
    }
}
