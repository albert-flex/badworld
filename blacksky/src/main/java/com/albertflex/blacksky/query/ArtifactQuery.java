package com.albertflex.blacksky.query;

import lombok.Data;

import java.util.Date;

@Data
public class ArtifactQuery {

    private Long artifactId;
    private Long userId;
    private String userName;
    private String name;
    private Date startDate;
    private Date endDate;
}
