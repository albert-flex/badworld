package com.natsufumij.blacksky.service;

import com.natsufumij.blacksky.domain.Announce;

import java.util.List;

public interface AnnounceServices {

    Announce post(Announce announce);
    Announce get(Long id);
    List<Announce> fetchNewest();
    Boolean delete(Long id);
}
