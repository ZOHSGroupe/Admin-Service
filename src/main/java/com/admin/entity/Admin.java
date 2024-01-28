package com.admin.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "admins")
@Setter @Getter @ToString
public class Admin {
    @Id
    private String id;
    private String username;
    private String email;
    private String password;
}
