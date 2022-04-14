package com.albertflex.blacksky.service.spi;

import com.albertflex.blacksky.domain.Announce;
import com.albertflex.blacksky.mapper.AnnounceMapper;
import com.albertflex.blacksky.service.AnnounceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnounceServicesSPI implements AnnounceServices {

    private final AnnounceMapper announceMapper;

    private final static Announce EMPTY_ANNOUNCE = new Announce();

    @Autowired
    public AnnounceServicesSPI(AnnounceMapper announceMapper) {
        this.announceMapper = announceMapper;
    }

    @Override
    public Announce post(Announce announce) {
        int result = announceMapper.post(announce);
        return result == 1 ? announce : EMPTY_ANNOUNCE;
    }

    @Override
    public Announce get(Long id) {
        Announce an = announceMapper.get(id);
        return an == null ? EMPTY_ANNOUNCE : an;
    }

    @Override
    public List<Announce> fetchNewest() {
        return announceMapper.fetchNewest();
    }

    @Override
    public Boolean delete(Long id) {
        return announceMapper.delete(id) == 1;
    }
}
