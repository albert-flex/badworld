/*
 */
package com.albertflex.blacksky.controller;

import com.albertflex.blacksky.domain.Post;
import com.albertflex.blacksky.query.PageData;
import com.albertflex.blacksky.query.PageQuery;
import com.albertflex.blacksky.query.PostQuery;
import com.albertflex.blacksky.service.PostServices;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/post")
public class PostController {
    
    private final PostServices postServices;
    
    @Autowired
    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }
    
    @GetMapping("/{postId}")
    public Post fetchPost(@PathVariable("postId") Long postId){
        return postServices.fetchPost(postId);
    }
    
    @GetMapping("/by_user/{userId}")
    public PageData<Post> fetchPostByUserId(@PathVariable("userId") Long userId, PageQuery page) {
        page.start();
        return PageData.of(postServices.fetchPostsByUserId(userId));
    }

    @GetMapping("/fetch/query")
    public PageData<Post> query(PostQuery query){
        if(query==null)return PageData.of(Collections.emptyList());
        query.start();

        return PageData.of(postServices.query(query));
    }

    @GetMapping("/fetch/newest")
    public PageData<Post> fetchNewsPost(PageQuery page) {
        page.start();
        return PageData.of(postServices.fetchNewPost());
    }
    
    @GetMapping("/by_post/{postId}")
    public PageData<Post> fetchPostsReplyOn(@PathVariable("postId") Long postId,PageQuery page) {
        page.start();
        return PageData.of(postServices.fetchPostsReplyOn(postId));
    }
    
    @PostMapping
    public Post post(@RequestBody Post p){
        return postServices.post(p.getUserId(),p.getTitle(), p.getContent());
    }
    
    @PostMapping("/reply")
    public Post reply(@RequestBody Post p){
        return postServices.reply(p.getUserId(), p.getContent(), p.getReplyPostId());
    }
    
    @DeleteMapping("/{postId}")
    public Boolean cancel(@PathVariable("postId") Long postId){
        return postServices.cancel(postId);
    }
}
