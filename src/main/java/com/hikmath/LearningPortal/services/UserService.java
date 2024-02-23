package com.hikmath.LearningPortal.services;

import com.hikmath.LearningPortal.Dto.UserDTO;
import com.hikmath.LearningPortal.entity.Category;
import com.hikmath.LearningPortal.entity.User;
import com.hikmath.LearningPortal.exceptions.ResourceNotFoundException;
import com.hikmath.LearningPortal.mapper.UserMapper;
import com.hikmath.LearningPortal.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;


    public UserDTO createUser(UserDTO userDTO) {
        log.info("Creating user: {}", userDTO);
        User user = userMapper.toEntity(userDTO);

        User savedUser = userRepository.save(user);
        return userMapper.toDto(savedUser);

    }

    public UserDTO updateUser(UserDTO userDTO, String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        User users=userMapper.toEntity(userDTO);
        User updatedUser = userRepository.save(users);
        return userMapper.toDto(updatedUser);
    }

    public UserDTO getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        return userMapper.toDto(user);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(user -> userMapper.toDto(user)).collect(Collectors.toList());
    }

    public void deleteUser(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        userRepository.delete(user);
    }

}