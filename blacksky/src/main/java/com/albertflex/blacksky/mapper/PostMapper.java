package com.albertflex.blacksky.mapper;

import com.albertflex.blacksky.domain.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PostMapper {

    //根据帖子Id获取帖子
    Post fetchPost(Long id);
    
    //根据用户Id获取已发布的帖子
    List<Post> fetchPostsByUserId(Long userId);

    List<Post> fetchNewsPosts(Integer size);
    
    //根据帖子Id获取所有直接的回复帖子
    List<Post> fetchPostsReplyOn(Long postId);

    //发布帖子
    int post(Post post);

    //删除帖子
    int deletePost(Long postId);

    //添加帖子回复数量
    int countAdd(@Param("postId") Long postId,@Param("count") int count);    

    //减少帖子回复数量
    int countDec(@Param("postId") Long postId,@Param("count") int count);    
}
