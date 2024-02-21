package com.hikmath.LearningPortal.controllers;

import com.hikmath.LearningPortal.Dto.ApiResponse;
import com.hikmath.LearningPortal.Dto.UserDTO;
import com.hikmath.LearningPortal.services.UserService;
import jakarta.validation.Valid;
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
    public ResponseEntity<UserDTO>createUser( @Valid @RequestBody UserDTO userDTO){
        UserDTO createUserDTO = this.userService.createUser(userDTO);
        return new ResponseEntity<>(createUserDTO, HttpStatus.CREATED);
    }

    //update a user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO , @PathVariable Long userId ){
        UserDTO updatedUser=this.userService.updateUser(userDTO,  userId );
        return ResponseEntity.ok(updatedUser);
    }


    //delete user
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable Long userId){
        this.userService.deleteUser(userId);
        return  new ResponseEntity<>( new ApiResponse("User deleted successfully",true), HttpStatus.OK);
    }

    //get All users
    @GetMapping("/")
    public ResponseEntity<List<UserDTO>>getAllUsers() {
        return ResponseEntity.ok(this.userService.getAllUsers());
    }
    //get one user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO>getUserById(@PathVariable long userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }
}