package com.learnspringboot.springbootproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnspringboot.springbootproject.entity.UserEntry;
import com.learnspringboot.springbootproject.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;

  @GetMapping
  public List<UserEntry> getAllUsers() {
    return userService.getAllUsers();
  }

  @PostMapping
  public void createUser(@RequestBody UserEntry user) {
    userService.saveUser(user);
  }

  @PutMapping
  public ResponseEntity<?> updateUser(@RequestBody UserEntry user) {
    UserEntry existingUser = userService.findUserByUserName(user.getUserName());
    if(existingUser != null){
      existingUser.setUserName(user.getUserName());
      existingUser.setPassword(user.getPassword());
      userService.saveUser(existingUser);

      return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
