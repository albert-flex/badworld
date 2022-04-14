package com.albertflex.blacksky.controller;

import com.albertflex.blacksky.domain.Profile;
import com.albertflex.blacksky.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserControllerTest extends AbstractControllerTest {

    @Test
    public void testFetch() throws Exception {

        User excepted;
        //Test Fetch
        excepted = getResult("/user/fetch/100", User.class);
        Assertions.assertEquals(100L, excepted.getId());
        Assertions.assertEquals("albert", excepted.getUserName());

        //Test Fetch With Profile
        excepted = getResult("/user/fetch_with_profile/100", User.class);
        Assertions.assertNotNull(excepted.getProfile());
        Assertions.assertEquals("albert@qq.com", excepted.getProfile().getEmail());

        //Test Fetch Profile
        Profile p = getResult("/user/fetch_profile/100", Profile.class);
        Assertions.assertEquals("albert@qq.com", p.getEmail());

        //Test Check Name
        Boolean excepted2 = getResult("/user/check_name/albert", Boolean.class);
        Assertions.assertTrue(excepted2);
    }

    @Test
    public void testRegister() throws Exception {
        //Test Register
        User user = new User();
        user.setState(1);
        user.setUserName("Shalock");
        user.setPassword("shalock123");
        Profile p = new Profile();
        p.setEmail("Shalock@163.com");
        user.setProfile(p);

        User dest = postResult("/user/register", user, User.class);
        Assertions.assertNotNull(dest);
        Assertions.assertNotNull(dest.getId());
        Assertions.assertEquals(user.getUserName(), dest.getUserName());

        //Test UnRegister
        Boolean destB = deleteResult("/user/unregister/" + dest.getId(),Boolean.class);
        Assertions.assertTrue(destB);
    }
}
