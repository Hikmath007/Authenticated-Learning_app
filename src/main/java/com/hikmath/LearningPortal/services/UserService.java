package com.hikmath.LearningPortal.services;


import com.hikmath.LearningPortal.Dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    UserDTO createUser(UserDTO user);
    UserDTO updateUser(UserDTO user, Long userId);
    UserDTO getUserById(Long userId);
    List<UserDTO>getAllUsers();
    void deleteUser(Long userId);


}
