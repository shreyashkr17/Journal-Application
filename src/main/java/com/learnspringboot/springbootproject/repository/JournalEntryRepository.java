package com.learnspringboot.springbootproject.repository;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.learnspringboot.springbootproject.entity.JournalEntity;

public interface JournalEntryRepository extends MongoRepository<JournalEntity, ObjectId>{
  
}
