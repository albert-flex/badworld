package com.albertflex.blacksky.service;

import com.albertflex.blacksky.domain.Profile;
import com.albertflex.blacksky.domain.User;
import com.albertflex.blacksky.BlackSky;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {BlackSky.class})
public class UserServicesTest {

    @Autowired
    private UserServices userServices;

    @Test
    public void test() {

        final String name = "夏文纯一";
        final String password = "NatsufumiJ";

        //Test Register User
        User user = new User();
        user.setUserName(name);
        user.setPassword(password);
        Profile profile = new Profile();
        profile.setEmail("NatsufumiJ@163.com");

        //Test Register,Contains
        Assertions.assertNull(userServices.fetchUserByName(name));
        Assertions.assertFalse(userServices.checkUserName(name));
        Assertions.assertNotNull(userServices.register(user, profile).getId());
        Assertions.assertNotNull(user.getId());
        Assertions.assertNotNull(userServices.fetchUserByName(name));
        Assertions.assertTrue(userServices.checkUserName(name));

        long _id=user.getId();
        //Test Re Register
        user.setId(null);
        Assertions.assertNull(userServices.register(user, profile).getId());

        //Test Fetch User
        Assertions.assertNotNull(userServices.fetchUser(100L));
        Assertions.assertNotNull(userServices.fetchUserByName("albert"));
        Assertions.assertNotNull(userServices.fetchUserWithProfile(100L));

        //Test Update User
        user.setId(_id);
        final String name2 = "夏文纯一2号";
        final String email2 = "NatsufumiJ2@163.com";
        User updateData = new User();
        updateData.setId(user.getId());
        updateData.setUserName(name2);
        Profile updateProfile = new Profile();
        updateProfile.setUserId(user.getId());
        updateProfile.setEmail(email2);

        Assertions.assertTrue(userServices.updateUser(updateData));
        Assertions.assertTrue(userServices.updateProfile(updateProfile));
        user = userServices.fetchUserWithProfile(user.getId());
        Assertions.assertEquals(name2, user.getUserName());
        Assertions.assertEquals(email2, user.getProfile().getEmail());


        //Test Login,Logout User
        User loginUser = new User();
        loginUser.setUserName(user.getUserName());
        loginUser.setPassword(password);
        Assertions.assertNotNull(userServices.logIn(loginUser).getId());
        loginUser.setId(null);
        Assertions.assertNull(userServices.logIn(loginUser).getId());
        Assertions.assertTrue(userServices.logOut(user.getId()));
        Assertions.assertFalse(userServices.logOut(user.getId()));

        //Test Lock,UnLock
        Assertions.assertTrue(userServices.lock(user.getId()));
        Assertions.assertFalse(userServices.lock(user.getId()));
        Assertions.assertTrue(userServices.unLock(user.getId()));
        Assertions.assertFalse(userServices.unLock(user.getId()));

        //Test UnRegister User
        Assertions.assertNotNull(userServices.fetchUser(user.getId()));
        Assertions.assertNotNull(userServices.fetchProfile(user.getId()));
        Assertions.assertTrue(userServices.unregister(user.getId()));
        Assertions.assertFalse(userServices.checkUserName(user.getUserName()));
        Assertions.assertNull(userServices.fetchUser(user.getId()).getId());
        Assertions.assertNull(userServices.fetchProfile(user.getId()).getUserId());
    }
}