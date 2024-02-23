package com.hikmath.LearningPortal.controllers;

import com.hikmath.LearningPortal.Dto.ApiResponse;
import com.hikmath.LearningPortal.Dto.UserDTO;
import com.hikmath.LearningPortal.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // add a user

    @PostMapping("/")
    public ResponseEntity<UserDTO>createUser(  @RequestBody UserDTO userDTO){
        UserDTO createUserDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
    }

    //update a user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO , @PathVariable String userId ){
        UserDTO updatedUser=userService.updateUser(userDTO,  userId );
        return ResponseEntity.ok(updatedUser);
    }


    //delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        return  new ResponseEntity<>( new ApiResponse("User deleted successfully",true), HttpStatus.OK);
    }

    //get All users
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>>getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    //get one user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO>getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

}