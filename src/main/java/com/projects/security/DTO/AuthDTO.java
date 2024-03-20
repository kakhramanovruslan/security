package com.projects.security.DTO;
// todo - change folder name to lowercase

import lombok.Data;

@Data
// todo - user camel case for class naming
public class AuthDTO {

    private String username;

    private String password;

}
