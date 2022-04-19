package com.albertflex.blacksky.service;

import com.albertflex.blacksky.domain.FileResource;

import java.util.List;

public interface FileResourceServices {

    List<FileResource> views();
    Boolean add(FileResource file);
    FileResource get(Long id);

    Long fetch(FileResource res);

    Boolean remove(Long id);
}
