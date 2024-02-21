package com.hikmath.LearningPortal.Dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;

     @NotEmpty//VALIDATION
     @Size(min=2, message = "your name should be atleast of 2 letters")
    private String name;

     @Email
    private String email;

     @NotEmpty
    private String password;
}
