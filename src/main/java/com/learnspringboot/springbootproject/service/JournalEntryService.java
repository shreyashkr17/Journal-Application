package com.learnspringboot.springbootproject.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.learnspringboot.springbootproject.entity.JournalEntity;
import com.learnspringboot.springbootproject.entity.UserEntry;
import com.learnspringboot.springbootproject.repository.JournalEntryRepository;

@Component
public class JournalEntryService {

  @Autowired
  private JournalEntryRepository journalEntryRepository;
  @Autowired
  private UserService userService;

  @Transactional
  public void saveEntry(JournalEntity journalEntity, String userName) {
    try {
      UserEntry user = userService.findUserByUserName(userName);
      journalEntity.setDate(LocalDateTime.now());
      JournalEntity journal = journalEntryRepository.save(journalEntity);
      user.getJournalEntries().add(journal);
      userService.saveUser(user);
    } catch (Exception e) {
      System.out.println(e);
      throw new RuntimeException("Error saving journal entry. ", e);
    }
  }

  public void saveEntry(JournalEntity journalEntity) {
    journalEntryRepository.save(journalEntity);
  }

  public List<JournalEntity> getAllJournalEntries() {
    return journalEntryRepository.findAll();
  }

  public Optional<JournalEntity> findJournalById(ObjectId id) {
    return journalEntryRepository.findById(id);
  }

  public void deleteJournalById(ObjectId id, String userName) {
    UserEntry user = userService.findUserByUserName(userName);
    user.getJournalEntries().removeIf(entry -> entry.getId().equals(id));
    userService.saveUser(user);
    journalEntryRepository.deleteById(id);
    ;
  }
}
