package com.albertflex.blacksky.service;

import com.albertflex.blacksky.domain.Profile;
import com.albertflex.blacksky.domain.User;

public interface AdminServices {

    User registerAdmin(User user, Profile p);
    Boolean removeAuthority(Long userId,Long authorityId);
    Boolean addAuthority(Long userId,Long authorityId);
}