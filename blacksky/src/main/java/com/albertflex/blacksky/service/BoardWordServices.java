package com.albertflex.blacksky.service;

import com.albertflex.blacksky.domain.BoardWord;

import java.util.List;

public interface BoardWordServices {

    BoardWord post(BoardWord word);
    BoardWord get(Long id);
    List<BoardWord> fetchNewest();
    Boolean delete(Long id);
}
