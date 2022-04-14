package com.albertflex.blacksky.service;

import com.albertflex.blacksky.domain.Post;

import java.util.List;

/**
 *
 * @author Administrator
 */
public interface PostServices {

    Post fetchPost(Long postId);
    List<Post> fetchPostsByUserId(Long userId);
    List<Post> fetchPostsReplyOn(Long postId);
    
    Post post(Long userId,String content);
    Post reply(Long userId,String content,Long repliedId);
    
    Boolean cancel(Long postId);
}
