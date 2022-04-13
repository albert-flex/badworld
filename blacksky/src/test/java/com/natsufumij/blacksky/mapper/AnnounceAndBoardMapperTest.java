package com.natsufumij.blacksky.mapper;

import com.natsufumij.blacksky.domain.Announce;
import com.natsufumij.blacksky.domain.BoardWord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class AnnounceAndBoardMapperTest {

    @Autowired
    private AnnounceMapper announceMapper;

    @Autowired
    private BoardWordMapper boardWordMapper;

    @Test
    public void testGet(){
        Announce announce=announceMapper.get(100L);
        Assertions.assertNotNull(announce.getId());
        Assertions.assertEquals("Official Website",announce.getTitle());

        List<Announce> newest=announceMapper.fetchNewest();
        Assertions.assertEquals(10,newest.size());

        BoardWord boardWord=boardWordMapper.get(100L);
        Assertions.assertNotNull(boardWord.getId());
        Assertions.assertEquals("kaka@qq.com",boardWord.getEmail());

        List<BoardWord> boardWords=boardWordMapper.fetchNewest();
        Assertions.assertEquals(10,boardWords.size());
    }

    @Test
    public void testChange(){
        Announce announce=new Announce();
        announce.setTitle("Want You");
        announce.setUserId(100L);
        announce.setContent("XXX");
        Assertions.assertEquals(1,announceMapper.post(announce));

        List<Announce> announces=announceMapper.fetchNewest();
        Assertions.assertEquals(10,announces.size());
        Assertions.assertEquals("Want You",announces.get(0).getTitle());

        BoardWord word=new BoardWord();
        word.setEmail("aaaa");
        word.setTitle("AXA");
        word.setContent("ASA");

        Assertions.assertEquals(1,boardWordMapper.post(word));
        List<BoardWord> boardWords=boardWordMapper.fetchNewest();
        Assertions.assertEquals(10,boardWords.size());
        Assertions.assertEquals("aaaa",boardWords.get(0).getEmail());

        announceMapper.delete(announce.getId());
        boardWordMapper.delete(word.getId());

        Announce exceptedA=announceMapper.get(announce.getId());
        Assertions.assertNull(exceptedA);

        BoardWord exceptedW=boardWordMapper.get(word.getId());
        Assertions.assertNull(exceptedW);
    }
}
