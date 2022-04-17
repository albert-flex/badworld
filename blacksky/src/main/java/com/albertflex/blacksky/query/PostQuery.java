package com.albertflex.blacksky.query;

import lombok.Data;

import java.util.Date;

@Data
public class PostQuery {
    private Long userId;
    private String userName;
    private String title;
    private Date startDate;
    private Date endDate;
}
