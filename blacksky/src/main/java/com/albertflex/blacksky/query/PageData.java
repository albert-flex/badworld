package com.albertflex.blacksky.query;


import com.github.pagehelper.Page;
import lombok.Data;

import java.util.List;

@Data
public class PageData<T> {

    //1开始
    private Integer pageIndex=0;
    private Integer pageSize=0;
    private Integer pageTotal=0;
    private Long dataTotal=0L;
    private List<T> data;

    public static <T> PageData<T> of(List<T> list){
        PageData<T> page=new PageData<>();
        Page<T> p=(Page<T>) list;
        if(list.size()==0||p!=null){
            page.setPageIndex(p.getPageNum());
            page.setPageSize(p.getPageSize());
            page.setData(p.getResult());
            page.setDataTotal(p.getTotal());
            page.setPageTotal(p.getPages());
        }
        return page;
    }

    public static <T> PageData<T> empty(){
        return new PageData<>();
    }
}
