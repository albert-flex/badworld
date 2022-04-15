package com.albertflex.blacksky.service.spi;

import com.albertflex.blacksky.domain.Post;
import com.albertflex.blacksky.mapper.PostMapper;
import com.albertflex.blacksky.service.PostServices;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostServicesSPI implements PostServices{

    private final PostMapper postMapper;
    
    private static final Post EMPTY_POST=new Post();

    @Autowired
    public PostServicesSPI(PostMapper postMapper) {
        this.postMapper = postMapper;
    }
    
    @Override
    public Post fetchPost(Long postId) {
        Post p= postMapper.fetchPost(postId);
        if(p==null) return EMPTY_POST;
        else return p;
    }

    @Override
    public List<Post> fetchNewPost() {
        return postMapper.fetchNewsPosts(10);
    }

    @Override
    public List<Post> fetchPostsByUserId(Long userId) {
        return postMapper.fetchPostsByUserId(userId);
    }

    @Override
    public List<Post> fetchPostsReplyOn(Long postId) {
        return postMapper.fetchPostsReplyOn(postId);
    }

    @Override
    public Post post(Long userId,String title, String content) {
        Post p=new Post();
        p.setContent(content);
        p.setTitle(title);
        p.setUserId(userId);
        int result=postMapper.post(p);
        if(result==0) return EMPTY_POST;

        return p;
    }

    @Override
    public Post reply(Long userId, String content, Long repliedId) {

        Post dest=postMapper.fetchPost(repliedId);
        if(dest==null)return EMPTY_POST;
        
        Post p=new Post();
        p.setContent(content);
        p.setTitle("回复");
        p.setUserId(userId);
        p.setReplyPostId(repliedId);
        postMapper.post(p);
        postMapper.countAdd(repliedId, 1);
        return p;
    }

    //仅仅只是删除当前的帖子，如果是回复贴，则在回复的帖子那里减一
    @Override
    public Boolean cancel(Long postId) {
        Post dest=postMapper.fetchPost(postId);
        if(dest==null)return false;

        if(!Objects.equals(dest.getReplyPostId(), Post.NONE_REPLY_ID)){
            postMapper.countDec(dest.getReplyPostId(), 1);
        }        
        postMapper.deletePost(postId);
        return true;
    }    
}
