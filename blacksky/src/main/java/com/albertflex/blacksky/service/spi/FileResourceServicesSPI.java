package com.albertflex.blacksky.service.spi;

import com.albertflex.blacksky.domain.FileResource;
import com.albertflex.blacksky.mapper.FileResourceMapper;
import com.albertflex.blacksky.service.FileResourceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileResourceServicesSPI implements FileResourceServices {

    private final FileResourceMapper mapper;

    @Autowired
    public FileResourceServicesSPI(FileResourceMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<FileResource> views() {
        return mapper.views();
    }

    @Override
    public Boolean add(FileResource file) {
        int result= mapper.add(file);
        if(result==0&&file.getId()==null)return false;
        else return true;
    }

    @Override
    public FileResource get(Long id) {
        return mapper.get(id);
    }

    @Override
    public Boolean remove(Long id) {
        int result= mapper.remove(id);
        return result==1;
    }
}
