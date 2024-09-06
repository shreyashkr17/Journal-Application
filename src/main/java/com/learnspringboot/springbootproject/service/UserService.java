package com.learnspringboot.springbootproject.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learnspringboot.springbootproject.entity.UserEntry;
import com.learnspringboot.springbootproject.repository.UserRepository;

@Component
public class UserService {
  @Autowired
  private UserRepository userRepository;

  public void saveUser(UserEntry user) {
    userRepository.save(user);
  }

  public List<UserEntry> getAllUsers() {
    return userRepository.findAll();
  }

  public Optional<UserEntry> findUserById(ObjectId id) {
    return userRepository.findById(id);
  }

  public void deleteUserById(ObjectId id){
    userRepository.deleteById(id);;
  }

  public UserEntry findUserByUserName(String userName) {
    return userRepository.findByUserName(userName);
  }
}
