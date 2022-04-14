package com.albertflex.blacksky.mapper;

import com.albertflex.blacksky.domain.Profile;
import com.albertflex.blacksky.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    //    获取用户信息和资料
    //    User fetchUserWithProfile(Long id);
    @Test
    public void testFetchUserWithProfile(){
        final String FAILED_WORD="Test FetchUserWithProfile Failed!";
        User user=userMapper.fetchUserWithProfile(100L);
        Assertions.assertEquals("albert",user.getUserName(),FAILED_WORD);
        Profile profile=user.getProfile();
        Assertions.assertEquals("albert@qq.com",profile.getEmail());
    }

    private void _insertUser(){
        User user=new User();
        user.setUserName("Albert Flex");
        user.setPassword("00000000");
        userMapper.postUser(user);

        Profile p=new Profile();
        p.setEmail("00000000");
        p.setUserId(user.getId());
        userMapper.postProfile(p);
    }

    //    仅仅获取用户信息
    //    User fetchUser(Long id);
    @Test
    public void testFetchUser(){
        final String FAILED_WORD="Test FetchUser Failed!";
        User user=userMapper.fetchUser(100L);
        Assertions.assertEquals("albert",user.getUserName(),FAILED_WORD);
    }

    //    通过用户名获取用户信息
    //    User fetchUserByName(String userName);
    @Test
    public void testFetchUserByName(){
        final String FAILED_WORD="Test FetchUserByName Failed!";
        String name="albert";
        User user=userMapper.fetchUserByName(name);
        Assertions.assertNotNull(user,FAILED_WORD);
    }

    //    获取用户资料
    //    Profile fetchProfile(Long userId);
    @Test
    public void testFetchProfile(){
        Profile profile=userMapper.fetchProfile(100L);
        Assertions.assertEquals("albert@qq.com",profile.getEmail());
    }

    //    判断用户名是否存在记录
    //    Boolean containsUser(String userName);
    @Test
    public void testContainsUser(){
        Boolean result=userMapper.containsUser("albert");
        Assertions.assertEquals(Boolean.TRUE,result);
    }

    //    添加用户
    //    int postUser(User user);
    @Test
    public void testPostUser(){
        final String pwd="xxxaaa";
        User user=new User();
        user.setUserName("-albert-flex-");
        user.setPassword(pwd);
        userMapper.postUser(user);
        Assertions.assertNotNull(user.getId());

        Profile profile=new Profile();
        profile.setUserId(user.getId());
        profile.setEmail("aaa@a.com");
        userMapper.postProfile(profile);

        User user1=userMapper.fetchUser(user.getId());
        Assertions.assertNotNull(user1);
        Assertions.assertTrue(userMapper.checkPassword(user1.getId(),pwd));

        userMapper.deleteUser(user.getId());
        userMapper.deleteProfile(user.getId());
    }

    //    更新用户信息，非空的字段都会被更新，根据Id查找
    //    int updateUser(User user);
    @Test
    public void testUpdateUser(){
        User user=new User();
        user.setUserName("Update-Albert");
        user.setPassword("XXX-XXX");
        userMapper.postUser(user);
        Profile profile=new Profile();
        profile.setEmail("xxx@qq.com");
        profile.setUserId(user.getId());
        userMapper.postProfile(profile);

        User user1=userMapper.fetchUserWithProfile(user.getId());
        Profile profile1=user1.getProfile();
        profile1.setEmail("Updated-Email@");
        user1.setUserName("Updated-Albert");
        userMapper.updateUser(user1);
        userMapper.updateProfile(profile1);
        user1=userMapper.fetchUserWithProfile(user.getId());
        Assertions.assertEquals("Updated-Albert",user1.getUserName());
        Assertions.assertEquals("Updated-Email@",user1.getProfile().getEmail());

        userMapper.deleteUser(user.getId());
        userMapper.deleteProfile(user.getId());
    }
}
