package com.learnspringboot.springbootproject.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learnspringboot.springbootproject.entity.JournalEntity;
import com.learnspringboot.springbootproject.entity.UserEntry;
import com.learnspringboot.springbootproject.service.JournalEntryService;
import com.learnspringboot.springbootproject.service.UserService;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
  @Autowired
  private JournalEntryService journalEntryService;
  @Autowired
  private UserService userService;

  @GetMapping("{userName}")
  public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
    UserEntry user = userService.findUserByUserName(userName);
    List<JournalEntity> journalEntries = user.getJournalEntries();
    if (journalEntries != null && !journalEntries.isEmpty()) {
      return new ResponseEntity(journalEntries, HttpStatus.OK);
    }
    return new ResponseEntity(HttpStatus.NOT_FOUND);
  }

  @PostMapping("{userName}")
  public ResponseEntity<JournalEntity> createEntry(@RequestBody JournalEntity myEntry, @PathVariable String userName) {
    try {
      journalEntryService.saveEntry(myEntry, userName);
      return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("id/{myId}")
  public ResponseEntity<JournalEntity> getJournalEntryById(@PathVariable ObjectId myId) {
    Optional<JournalEntity> journalEntry = journalEntryService.findJournalById(myId);
    return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK))
        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("id/{userName}/{id}")
  public ResponseEntity<?> deleteJournalEntityById(@PathVariable ObjectId id, @PathVariable String userName) {
    journalEntryService.deleteJournalById(id, userName);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @PutMapping("id/{userName}/{id}")
  public ResponseEntity<JournalEntity> updateJournalEntityById(@PathVariable ObjectId id,
      @RequestBody JournalEntity myEntry, @PathVariable String userName) {
    JournalEntity prev = journalEntryService.findJournalById(id).orElse(null);
    if (prev != null) {
      prev.setTitle(
          myEntry.getTitle() != null && !myEntry.getTitle().equals("") ? myEntry.getTitle() : prev.getTitle());
      prev.setSummary(
          myEntry.getSummary() != null && !myEntry.getSummary().equals("") ? myEntry.getSummary() : prev.getSummary());
      journalEntryService.saveEntry(prev);
      return new ResponseEntity<>(prev, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
