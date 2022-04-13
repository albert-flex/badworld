package com.natsufumij.blacksky.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BoardWord {

    private Long id;
    private String email;
    private String title;
    private Date leftTime;
    private String content;
}
