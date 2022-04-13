package com.natsufumij.blacksky.domain;

import lombok.Data;

import java.util.Date;

@Data
public class ArtifactType {
    private Long id;
    private String name;
    private Date createTime;
    private Long userId;
}
