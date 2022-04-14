package com.albertflex.blacksky.service;

import com.albertflex.blacksky.domain.Announce;
import com.albertflex.blacksky.domain.BoardWord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AnnounceBoardServicesTest {

    @Autowired
    private AnnounceServices announceServices;

    @Autowired
    private BoardWordServices boardWordServices;

    @Test
    public void testGet(){
        Announce announce=announceServices.get(100L);
        Assertions.assertNotNull(announce.getId());
        Assertions.assertEquals("Official Website",announce.getTitle());

        List<Announce> newest=announceServices.fetchNewest();
        Assertions.assertEquals(10,newest.size());

        BoardWord boardWord=boardWordServices.get(100L);
        Assertions.assertNotNull(boardWord.getId());
        Assertions.assertEquals("kaka@qq.com",boardWord.getEmail());

        List<BoardWord> boardWords=boardWordServices.fetchNewest();
        Assertions.assertEquals(10,boardWords.size());
    }

    @Test
    public void testChange(){
        Announce announce=new Announce();
        announce.setTitle("Want You");
        announce.setUserId(100L);
        announce.setContent("XXX");
        Assertions.assertNotNull(announceServices.post(announce).getId());

        List<Announce> announces=announceServices.fetchNewest();
        Assertions.assertEquals(10,announces.size());
        Assertions.assertEquals("Want You",announces.get(0).getTitle());

        BoardWord word=new BoardWord();
        word.setEmail("aaaa");
        word.setTitle("AXA");
        word.setContent("ASA");

        Assertions.assertNotNull(boardWordServices.post(word).getId());
        List<BoardWord> boardWords=boardWordServices.fetchNewest();
        Assertions.assertEquals(10,boardWords.size());
        Assertions.assertEquals("aaaa",boardWords.get(0).getEmail());

        Assertions.assertTrue(announceServices.delete(announce.getId()));
        Assertions.assertTrue(boardWordServices.delete(word.getId()));

        Announce exceptedA=announceServices.get(announce.getId());
        Assertions.assertNull(exceptedA.getId());

        BoardWord exceptedW=boardWordServices.get(word.getId());
        Assertions.assertNull(exceptedW.getId());
    }
}
