package com.admin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString @Getter @Setter
public class AdminUpdateDTO {
    private String username;
    private String email;
    private String password;
}
