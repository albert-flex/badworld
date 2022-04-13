package com.natsufumij.blacksky.service.spi;

import com.natsufumij.blacksky.domain.BoardWord;
import com.natsufumij.blacksky.mapper.BoardWordMapper;
import com.natsufumij.blacksky.service.BoardWordServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardWordServicesSPI implements BoardWordServices {

    private final BoardWordMapper boardWordMapper;

    private static final BoardWord EMPTY_WORD = new BoardWord();

    @Autowired
    public BoardWordServicesSPI(BoardWordMapper boardWordMapper) {
        this.boardWordMapper = boardWordMapper;
    }

    @Override
    public BoardWord post(BoardWord word) {
        int result = boardWordMapper.post(word);
        return result == 1 ? word : EMPTY_WORD;
    }

    @Override
    public BoardWord get(Long id) {
        BoardWord b = boardWordMapper.get(id);
        return b == null ? EMPTY_WORD : b;
    }

    @Override
    public List<BoardWord> fetchNewest() {
        return boardWordMapper.fetchNewest();
    }

    @Override
    public Boolean delete(Long id) {
        return boardWordMapper.delete(id) == 1;
    }
}
