package com.albertflex.blacksky.controller;

import com.albertflex.blacksky.domain.Profile;
import com.albertflex.blacksky.domain.User;
import com.albertflex.blacksky.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
public class UserController{

    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping("register")
    public User register(@RequestBody User user) {
        return userServices.register(user,user.getProfile()).modernPass();
    }


    @PostMapping("self")
    public Long self(@AuthenticationPrincipal User user){
        return user.getId();
    }

    @DeleteMapping("unregister/{id}")
    public Boolean unregister(@PathVariable("id") Long id) {
        return userServices.unregister(id);
    }

    @PutMapping("login")
    public User logIn(User user) {
        return userServices.logIn(user).modernPass();
    }

    @PutMapping("logout/{id}")
    public Boolean logOut(@PathVariable("id") Long id) {
        return userServices.logOut(id);
    }

    @PutMapping("lock/{id}")
    public Boolean lock(@PathVariable("id") Long id) {
        return userServices.lock(id);
    }

    @PutMapping("unlock/{id}")
    public Boolean unLock(@PathVariable("id") Long id) {
        return userServices.unLock(id);
    }

    @GetMapping("check_name/{name}")
    public Boolean checkUserName(@PathVariable("name") String userName) {
        return userServices.checkUserName(userName);
    }

    @GetMapping("fetch/{id}")
    public User fetchUser(@PathVariable("id") Long id) {
        return userServices.fetchUser(id).modernPass();
    }

    @GetMapping("fetch_with_profile/{id}")
    public User fetchUserWithProfile(@PathVariable("id") Long id) {
        return userServices.fetchUserWithProfile(id).modernPass();
    }

    @GetMapping("fetch_by_name/{name}")
    public User fetchUserByName(@PathVariable("name") String userName) {
        return userServices.fetchUserByName(userName).modernPass();
    }

    @GetMapping("fetch_profile/{userId}")
    public Profile fetchProfile(@PathVariable("userId") Long userId) {
        return userServices.fetchProfile(userId);
    }

    @PutMapping("update")
    public Boolean updateUser(@RequestBody User user) {
        return userServices.updateUser(user);
    }

    @PutMapping("update_and_profile")
    public Boolean updateUserWithProfile(@RequestBody User user){
        if(!userServices.updateUser(user))return false;
        return userServices.updateProfile(user.getProfile());
    }

    @PutMapping("update_profile")
    public Boolean updateProfile(@RequestBody Profile profile) {
        return userServices.updateProfile(profile);
    }
}
