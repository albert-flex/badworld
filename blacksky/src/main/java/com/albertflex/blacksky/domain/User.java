package com.albertflex.blacksky.domain;

import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.List;

@Data
public class User implements UserDetails {

    private Long id;
    private String userName;
    private String password;
    private Date createTime;
    private Date lastLogin;

    //default   1 Normal
    //          2 Login
    //          3 Locked
    private Integer state;

    private Profile profile;

    private List<Authority> authorities;

    @Override
    public String getUsername() {
        return userName;
    }
    
    public String getUserName(){
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !state.equals(3);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(state==null)return true;
        return !state.equals(3);
    }

    @Override
    public String getPassword() {
        return password;
    }

    public User modernPass(){
        password="xxxx";
        return this;
    }
}
