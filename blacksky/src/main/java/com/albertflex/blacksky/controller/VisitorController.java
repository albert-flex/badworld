package com.albertflex.blacksky.controller;

import com.albertflex.blacksky.domain.Announce;
import com.albertflex.blacksky.domain.BoardWord;
import com.albertflex.blacksky.service.AnnounceServices;
import com.albertflex.blacksky.service.BoardWordServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visit")
public class VisitorController {

    private final AnnounceServices announceServices;
    private final BoardWordServices boardWordServices;

    public VisitorController(AnnounceServices announceServices, BoardWordServices boardWordServices) {
        this.announceServices = announceServices;
        this.boardWordServices = boardWordServices;
    }

    @GetMapping("/announce/{id}")
    public Announce get(@PathVariable("id") Long id){
        return announceServices.get(id);
    }

    @GetMapping("/announce/newest")
    public List<Announce> newest(){
        return announceServices.fetchNewest();
    }

    @PostMapping("/board_word")
    public Boolean postBoardWord(@RequestBody BoardWord word){
        BoardWord boardWord=boardWordServices.post(word);
        return boardWord.getId() != null;
    }

    @GetMapping("/board_word/{id}")
    public BoardWord getWord(@PathVariable("id") Long id){
        return boardWordServices.get(id);
    }

    @GetMapping("/board_word/newest")
    public List<BoardWord> words(){
        return boardWordServices.fetchNewest();
    }
}
