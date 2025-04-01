package com.projects.e_commerce_service.dtos;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    private String username;
    private String password;
}
