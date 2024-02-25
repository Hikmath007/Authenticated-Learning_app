package com.hikmath.LearningPortal.Dto;

import lombok.Data;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class UserDTO {

    private String id;

    private String name;

    private String email;

    private String password;
}
