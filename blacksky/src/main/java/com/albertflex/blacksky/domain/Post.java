package com.albertflex.blacksky.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Post {
    public static Long NONE_REPLY_ID=0L;

    private Long id;
    private Long userId;
    private Date createTime;
    private String content;
    private Long replyCount;
    
    //默认值为0，表示此帖子非回复贴，否则表示回复的帖子Id
    private Long replyPostId=NONE_REPLY_ID;
    
}
