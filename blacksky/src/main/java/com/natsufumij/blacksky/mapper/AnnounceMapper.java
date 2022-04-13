package com.natsufumij.blacksky.mapper;

import com.natsufumij.blacksky.domain.Announce;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnounceMapper {

    int post(Announce announce);
    Announce get(Long id);
    List<Announce> fetchNewest();
    int delete(Long id);
}
