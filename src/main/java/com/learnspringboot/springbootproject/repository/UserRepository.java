package com.learnspringboot.springbootproject.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.learnspringboot.springbootproject.entity.UserEntry;

public interface UserRepository extends MongoRepository<UserEntry, ObjectId>{
  UserEntry findByUserName(String userName);
}
