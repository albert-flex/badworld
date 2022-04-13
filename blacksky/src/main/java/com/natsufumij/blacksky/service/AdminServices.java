package com.natsufumij.blacksky.service;

import com.natsufumij.blacksky.domain.Profile;
import com.natsufumij.blacksky.domain.User;

public interface AdminServices {

    User registerAdmin(User user, Profile p);
    Boolean removeAuthority(Long userId,Long authorityId);
    Boolean addAuthority(Long userId,Long authorityId);
}