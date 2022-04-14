package com.albertflex.blacksky.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FileResource implements Serializable {

    //文件id
    private Long id;
    //文件单名
    private String name;
    //文件后缀名
    private String format;
    //上传时间
    private Date uploadTime;
}
