package com.albertflex.blacksky.mapper;

import com.albertflex.blacksky.domain.FileResource;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileResourceMapper {

    List<FileResource> views();
    FileResource get(Long id);
    int add(FileResource resource);
    int remove(Long id);
}
