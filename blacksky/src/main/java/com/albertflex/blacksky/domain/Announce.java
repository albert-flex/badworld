package com.albertflex.blacksky.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Announce {

    private Long id;
    private Long userId;
    private String title;
    private Date publishTime;
    private String content;
}
