package com.projects.security.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AuthDto {

    @NotEmpty(message = "Username is a required field")
    private String username;

    @NotEmpty(message = "Password is a required field")
    private String password;

}
