package com.albertflex.blacksky.domain;

import lombok.Data;

import java.util.Date;

@Data
public class BoardWord {

    private Long id;
    private String email;
    private Date leftTime;
    private String content;
}
