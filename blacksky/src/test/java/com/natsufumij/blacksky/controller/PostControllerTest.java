/*
 */
package com.natsufumij.blacksky.controller;

import com.natsufumij.blacksky.domain.Post;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Administrator
 */
public class PostControllerTest extends AbstractControllerTest{

    @Test
    public void testFetch() throws Exception {
        //Test Correct
        Post excepted;
        excepted = getResult("/post/100", Post.class);
        Assertions.assertEquals(100L, excepted.getId());
        Assertions.assertEquals(100L, excepted.getUserId());

        //Test Fail
        excepted = getResult("/post/200", Post.class);
        Assertions.assertNull(excepted.getId());

        //Test Correct
        List<Post> exceptList = getListResult("/post/by_user/100",Post.class);
        Assertions.assertEquals(3, exceptList.size());

        //Test Correct
        exceptList = getListResult("/post/by_post/105",Post.class);
        Assertions.assertEquals(2, exceptList.size());
    }

    @Test
    public void testPost() throws Exception {
        //Test Correct
        Post excepted;
        Post p = new Post();
        p.setUserId(100L);
        p.setContent("I know.");

        excepted = postResult("/post",p, Post.class);
        Assertions.assertNotNull(excepted.getId());
        Assertions.assertEquals("I know.", excepted.getContent());

        deleteResult("/post/"+excepted.getId(),Boolean.class);
    }

    @Test
    public void testReply() throws Exception {
        //Test Correct
        Post p = new Post();
        p.setUserId(100L);
        p.setReplyPostId(105L);
        p.setContent("You Know.");

        Post excepted = postResult("/post/reply",p, Post.class);
        Assertions.assertNotNull(excepted.getId());

        deleteResult("/post/"+excepted.getId(),Boolean.class);

        //Test Fail
        p = new Post();
        p.setUserId(100L);
        p.setReplyPostId(300L);
        p.setContent("You Dont Know.");

        excepted = postResult("/post/reply",p, Post.class);
        Assertions.assertNull(excepted.getId());
    }
}
