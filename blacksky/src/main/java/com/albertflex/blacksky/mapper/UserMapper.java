package com.albertflex.blacksky.mapper;

import com.albertflex.blacksky.domain.Profile;
import com.albertflex.blacksky.domain.User;
import com.albertflex.blacksky.domain.UserAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {

    //获取用户信息和资料
    User fetchUserWithProfile(Long id);

    //仅仅获取用户信息
    User fetchUser(Long id);

    //通过用户名获取用户信息
    User fetchUserByName(String userName);

    //获取用户资料
    Profile fetchProfile(Long userId);

    //判断用户名是否存在记录
    //如果有，返回TRUE,没有，返回null
    Boolean containsUser(String userName);

    //检查密码是否一致
    //如果一致，返回TRUE,若不一致，返回null
    Boolean checkPassword(@Param("id") Long id,@Param("password") String password);

    //添加用户
    int postUser(User user);

    //添加用户资料
    int postProfile(Profile profile);

    //更新用户信息，非空的字段都会被更新，根据Id查找
    int updateUser(User user);

    //更新用户资料，非空的字段都会被更新，根据userId查找
    int updateProfile(Profile profile);

    //删除用户信息
    int deleteUser(Long id);

    //删除用户资料
    int deleteProfile(Long userId);

    int insertAuthority(UserAuthority ua);

    int deleteUserAuthority(Long userId);

    int deleteUserAtAuthority(@Param("userId") Long userId,@Param("authorityId") Long authorityId);
}
