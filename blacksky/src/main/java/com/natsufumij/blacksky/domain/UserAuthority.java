package com.natsufumij.blacksky.domain;

import lombok.Data;

@Data
public class UserAuthority {

    private Long id;
    private Long userId;
    private Long authorityId;
}
