package com.natsufumij.blacksky.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Artifact {

    private Long id;
    private Long userId;
    private Long typeId;
    private String name;
    private String version;
    private Date createTime;
    private Date updateTime;
    private String title;
    private String content;
}
