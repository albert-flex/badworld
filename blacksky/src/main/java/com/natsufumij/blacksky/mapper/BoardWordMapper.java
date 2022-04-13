package com.natsufumij.blacksky.mapper;


import com.natsufumij.blacksky.domain.BoardWord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardWordMapper {

    int post(BoardWord BoardWord);
    BoardWord get(Long id);
    List<BoardWord> fetchNewest();
    int delete(Long id);
}
