package com.learnspringboot.springbootproject.entity;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;
import lombok.Data;
import lombok.NonNull;

@Document(collection = "users")
@Data
public class UserEntry {
  @Id
  private ObjectId id;
  @Indexed(unique = true)
  @NonNull
  private String userName;
  @NonNull
  private String password;
  @DBRef
  private List<JournalEntity> journalEntries = new ArrayList<>();
  private List<String> roles;
}
