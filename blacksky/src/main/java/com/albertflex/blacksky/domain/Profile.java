package com.albertflex.blacksky.domain;

import lombok.Data;

@Data
public class Profile {

    private Long userId;
    private String email;
    private String phone;
    private String address;
    private String website;
    private String work;
    private String company;
}
