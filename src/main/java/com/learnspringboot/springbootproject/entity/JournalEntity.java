package com.learnspringboot.springbootproject.entity;
import java.time.LocalDateTime;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Document(collection = "journal_entries")
@Data
@NoArgsConstructor
public class JournalEntity {
  @Id
  private ObjectId id;
  @NonNull
  private String title;
  private String summary;
  private LocalDateTime date;
}
