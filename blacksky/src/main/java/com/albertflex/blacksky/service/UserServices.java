package com.albertflex.blacksky.service;

import com.albertflex.blacksky.domain.Profile;
import com.albertflex.blacksky.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserServices extends UserDetailsService{

    User register(User user, Profile p);
    Boolean unregister(Long id);

    User logIn(User user);
    Boolean logOut(Long id);

    Boolean lock(Long id);
    Boolean unLock(Long id);

    Boolean checkUserName(String userName);

    User fetchUser(Long id);
    User fetchUserWithProfile(Long id);
    User fetchUserByName(String userName);
    Profile fetchProfile(Long userId);

    Boolean updateUser(User user);
    Boolean updateProfile(Profile profile);
}
