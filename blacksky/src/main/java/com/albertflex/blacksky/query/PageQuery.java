package com.albertflex.blacksky.query;

import com.github.pagehelper.PageHelper;
import lombok.Data;

@Data
public class PageQuery{
    private Integer pageSize=1;
    private Integer pageIndex=10;

    public void start(){
        PageHelper.startPage(pageIndex,pageSize);
    }
}
