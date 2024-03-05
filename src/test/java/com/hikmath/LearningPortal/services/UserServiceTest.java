package com.hikmath.LearningPortal.services;

import com.hikmath.LearningPortal.Dto.UserDTO;
import com.hikmath.LearningPortal.entity.User;
import com.hikmath.LearningPortal.exceptions.ResourceNotFoundException;
import com.hikmath.LearningPortal.mapper.UserMapper;
import com.hikmath.LearningPortal.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        // Arrange
        UserDTO userDTO = new UserDTO();
        User user = new User();
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);

        // Act
        UserDTO result = userService.createUser(userDTO);

        // Assert
        assertNotNull(result);
        verify(userMapper, times(1)).toEntity(userDTO);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        String userId = "user_01";
        UserDTO userDTO = new UserDTO();
        User user = new User();
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userRepository.save(user)).thenReturn(user);

        // Act
        UserDTO result = userService.updateUser(userDTO, userId);

        // Assert
        assertNotNull(result);
        verify(userRepository, times(1)).findById(userId);
        verify(userMapper, times(1)).toEntity(userDTO);
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUserById() {
        // Arrange
        String userId = "user_01";
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(new UserDTO());

        // Act
        UserDTO result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        verify(userRepository, times(1)).findById(userId);
        verify(userMapper, times(1)).toDto(user);
    }

    @Test
    public void testGetAllUsers() {
        // Arrange
        List<User> users = Arrays.asList(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(new UserDTO());

        // Act
        List<UserDTO> result = userService.getAllUsers();

        // Assert
        assertNotNull(result);
        assertEquals(users.size(), result.size());
        verify(userRepository, times(1)).findAll();
        verify(userMapper, times(users.size())).toDto(any(User.class));
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        String userId = "user_01";
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    public void testDeleteUserNotFound() {
        // Arrange
        String userId = "user_01";
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> userService.deleteUser(userId));
        verify(userRepository, times(1)).findById(userId);
        verify(userRepository, never()).delete(any(User.class));
    }
}
