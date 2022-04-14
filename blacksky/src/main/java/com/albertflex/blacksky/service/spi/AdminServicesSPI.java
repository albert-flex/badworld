package com.albertflex.blacksky.service.spi;

import com.albertflex.blacksky.domain.Profile;
import com.albertflex.blacksky.domain.User;
import com.albertflex.blacksky.domain.UserAuthority;
import com.albertflex.blacksky.mapper.UserMapper;
import com.albertflex.blacksky.service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServicesSPI implements AdminServices {

    private final UserMapper userMapper;

    @Autowired
    public AdminServicesSPI(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User registerAdmin(User user, Profile p) {
        if (user == null || p == null) return user;
        if (user.getId() != null || p.getUserId() != null) return user;

        userMapper.postUser(user);
        if (user.getId() == null) return user;

        p.setUserId(user.getId());
        userMapper.postProfile(p);

        UserAuthority ua = new UserAuthority();
        ua.setUserId(user.getId());
        ua.setAuthorityId(2L);
        userMapper.insertAuthority(ua);
        user.setProfile(p);
        return user;
    }

    @Override
    public Boolean removeAuthority(Long userId, Long authorityId) {
        return userMapper.deleteUserAtAuthority(userId, authorityId) == 1;
    }

    @Override
    public Boolean addAuthority(Long userId, Long authorityId) {
        UserAuthority au = new UserAuthority();
        au.setAuthorityId(authorityId);
        au.setUserId(userId);
        return userMapper.insertAuthority(au) == 1;
    }
}
