/*
 */
package com.albertflex.blacksky.controller;

import com.albertflex.blacksky.domain.Post;
import com.albertflex.blacksky.service.PostServices;
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
    public List<Post> fetchPostByUserId(@PathVariable("userId") Long userId){
        return postServices.fetchPostsByUserId(userId);
    }
    
    @GetMapping("/by_post/{postId}")
    public List<Post> fetchPostsReplyOn(@PathVariable("postId") Long postId){
        return postServices.fetchPostsReplyOn(postId);
    }
    
    @PostMapping
    public Post post(@RequestBody Post p){
        return postServices.post(p.getUserId(), p.getContent());
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
