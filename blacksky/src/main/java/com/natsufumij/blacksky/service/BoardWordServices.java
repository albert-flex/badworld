package com.natsufumij.blacksky.service;

import com.natsufumij.blacksky.domain.BoardWord;

import java.util.List;

public interface BoardWordServices {

    BoardWord post(BoardWord word);
    BoardWord get(Long id);
    List<BoardWord> fetchNewest();
    Boolean delete(Long id);
}
