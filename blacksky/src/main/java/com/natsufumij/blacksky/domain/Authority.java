package com.natsufumij.blacksky.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class Authority implements GrantedAuthority {

    private Long id;
    private String name;
    private String detail;

    @Override
    public String getAuthority() {
        return name;
    }
}
