package com.hikmath.LearningPortal.services.ServiceImpl;

import com.hikmath.LearningPortal.Dto.UserDTO;
import com.hikmath.LearningPortal.entity.User;
import com.hikmath.LearningPortal.exceptions.ResourceNotFoundException;
import com.hikmath.LearningPortal.repository.UserRepository;
import com.hikmath.LearningPortal.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public UserDTO createUser(UserDTO userDTO) {
            User user = this.dtoToUser(userDTO);    //dtoToUser method is below
            User savedUser = this.userRepository.save(user);
            return this.userToDTO(savedUser);

    }
    @Override
    public UserDTO updateUser(UserDTO userDTO, Long userId) {
        User user= this.userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());

        User updatedUser= this.userRepository.save(user);
        return this.userToDTO(updatedUser);
    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user= this.userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
        return userToDTO(user) ;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        List<User> users= this.userRepository.findAll();
        return users.stream().map(user->this.userToDTO(user)).collect(Collectors.toList());
    }

    @Override
    public void  deleteUser(Long userId) {
        User user= this.userRepository.findById(userId).
                orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
        this.userRepository.delete(user);
    }

    private User dtoToUser(UserDTO userDTO){
        log.info("UserDTO before mapping: {}", userDTO);
        User user= this.modelMapper.map(userDTO, User.class);
        log.info("User after mapping: {}", user);

        return user;

        /*Instead of converting one source to another manually we have used modelmapper
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword()); */

    }
    public UserDTO userToDTO(User user){
        return this.modelMapper.map(user, UserDTO.class);

      /*Instead of converting one source to another manually we have used modelmapper
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(userDTO.getPassword());*/

    }
}
