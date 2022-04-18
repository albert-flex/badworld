package com.albertflex.blacksky.query;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ArtifactQuery extends PageQuery{

    private Long artifactId;
    private Long userId;
    private String userName;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
