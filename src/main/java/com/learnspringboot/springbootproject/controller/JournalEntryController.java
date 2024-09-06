// package com.learnspringboot.springbootproject.controller;

// import java.util.ArrayList;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.learnspringboot.springbootproject.entity.JournalEntity;

// @RestController
// @RequestMapping("/journal")
// public class JournalEntryController {
//   private Map<Long, JournalEntity> journalEntries = new HashMap<>();

//   @GetMapping
//   public List<JournalEntity> getAllJournalEntries() {
//     return new ArrayList<>(journalEntries.values());
//   }

//   @PostMapping
//   public boolean createEntry(@RequestBody JournalEntity myEntry){
//     journalEntries.put(myEntry.getId(), myEntry);
//     return true;
//   }

//   @GetMapping("id/{id}")
//   public JournalEntity getJournalEntryById(@PathVariable Long id) {
//     return journalEntries.get(id);
//   }

//   @DeleteMapping("id/{id}")
//   public JournalEntity deleteJournalEntityById(@PathVariable Long id) {
//     return journalEntries.remove(id);
//   }

//   @PutMapping("id/{id}")
//   public JournalEntity updateJournalEntityById(@PathVariable Long id, @RequestBody JournalEntity myEntry) {
//     journalEntries.put(id, myEntry);
//     return myEntry;
//   }
// }
