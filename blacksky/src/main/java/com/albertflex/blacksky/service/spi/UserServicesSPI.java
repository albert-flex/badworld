package com.albertflex.blacksky.service.spi;

import com.albertflex.blacksky.domain.Profile;
import com.albertflex.blacksky.domain.User;
import com.albertflex.blacksky.domain.UserAuthority;
import com.albertflex.blacksky.mapper.UserMapper;
import com.albertflex.blacksky.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServicesSPI implements UserServices {

    private final UserMapper userMapper;
    private static final User EMPTY_USER = new User();
    private static final Profile EMPTY_PROFILE=new Profile();

    @Autowired
    public UserServicesSPI(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * User: Id Must Null, UserName, PassWord Need
     * Profile: UserId Must Null, Email Need
     */
    @Override
    public User register(User user, Profile p) {
        if (user == null || p == null) return user;
        if (user.getId() != null || p.getUserId() != null) return user;

        if (checkUserName(user.getUserName())) {
            return user;
        }

        userMapper.postUser(user);
        if (user.getId() == null) return user;

        p.setUserId(user.getId());
        userMapper.postProfile(p);

        UserAuthority ua = new UserAuthority();
        ua.setUserId(user.getId());
        ua.setAuthorityId(1L);
        userMapper.insertAuthority(ua);
        user.setProfile(p);
        return user;
    }

    @Override
    public Boolean unregister(Long id) {
        if (id == null) return false;

        User user = userMapper.fetchUser(id);
        if (user == null) return false;

        userMapper.deleteUser(id);
        userMapper.deleteProfile(id);
        userMapper.deleteUserAuthority(id);
        return true;
    }

    /**
     * Need UserName,Password
     */
    @Override
    public User logIn(User user) {
        if (user == null) return null;

        User dest = userMapper.fetchUserByName(user.getUserName());
        if (dest == null) return user;

        //Check Password
        Boolean result = userMapper.checkPassword(dest.getId(), user.getPassword());
        if (result == Boolean.TRUE) {
            //Check State
            Integer state = dest.getState();
            if (state != 1) {
                //Locked Or Login
                return user;
            } else {
                _updateState(dest, 2);
            }
        } else {
            return user;
        }

        user.setId(dest.getId());
        return user;
    }

    @Override
    public Boolean logOut(Long id) {
        if (id == null) return false;

        User dest = userMapper.fetchUser(id);
        if (dest == null) return false;

        //Check State
        Integer state = dest.getState();
        if (state != 2) {
            return false;
        } else {
            _updateState(dest, 1);
        }

        return true;
    }

    @Transactional
    public void _updateState(User user, Integer state) {
        User updateData = new User();
        updateData.setId(user.getId());
        updateData.setState(state);
        userMapper.updateUser(updateData);
    }

    @Override
    public Boolean lock(Long id) {
        if (id == null) return false;

        User user = userMapper.fetchUser(id);
        if (user == null) return false;

        Integer state = user.getState();
        //Check State
        if (state == 3) {
            return false;
        } else {
            _updateState(user, 3);
        }

        return true;
    }

    @Override
    public Boolean unLock(Long id) {
        if (id == null) return false;

        User user = userMapper.fetchUser(id);
        if (user == null) return false;

        Integer state = user.getState();
        //Check State
        if (state != 3) {
            return false;
        } else {
            _updateState(user, 1);
        }

        return true;
    }

    @Override
    public Boolean checkUserName(String userName) {
        Boolean result = userMapper.containsUser(userName);
        return result != null;
    }

    @Override
    public User fetchUser(Long id) {
        User dest = userMapper.fetchUser(id);
        return dest == null ? EMPTY_USER : dest;
    }

    @Override
    public User fetchUserWithProfile(Long id) {
        return userMapper.fetchUserWithProfile(id);
    }

    @Override
    public User fetchUserByName(String userName) {
        return userMapper.fetchUserByName(userName);
    }

    @Override
    public Boolean updateUser(User user) {
        return userMapper.updateUser(user) == 1;
    }

    @Override
    public Boolean updateProfile(Profile profile) {
        return userMapper.updateProfile(profile) == 1;
    }

    @Override
    public Profile fetchProfile(Long userId) {
        Profile profile= userMapper.fetchProfile(userId);
        return profile==null?EMPTY_PROFILE:profile;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = fetchUserByName(username);
        if (u == null) {
            throw new UsernameNotFoundException("name: " + username);
        } else {
            return u;
        }
    }
}
