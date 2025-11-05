package com.Journal.journalApp.controller;

/**
 *
 * this controller handle multiple user
 * with sequential CRUD operation on both userrepo as well as Journal repo
 * JournalEntryController - >  JournalService / UserService - >  UserRepo - > MongoDB
 *
 */


import com.Journal.journalApp.Service.UserService;
import com.Journal.journalApp.Service.journalService;
import com.Journal.journalApp.Model.JournalEntity;
import com.Journal.journalApp.Model.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Journal")
@CrossOrigin
public class JournalEntryController {

    @Autowired
    private journalService journalService;

    @Autowired
    private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?> getAllJournalEntries(@PathVariable String username) {
        UserEntity user = userService.findByUsername(username);
        List<JournalEntity> list = user.getJournalEntries();
        if (!list.isEmpty()) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<JournalEntity> saveEntry(@RequestBody JournalEntity journalEntity, @PathVariable String username) {
        try {
            journalEntity.setDate(LocalDateTime.now());
            journalService.saveEntry(journalEntity, username);
            return new ResponseEntity<>(journalEntity, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<?> getById(@PathVariable ObjectId myId) {
        Optional<JournalEntity> journalEntry = journalService.findById(myId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("{username}/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId, @PathVariable String username) {
        journalService.delete(myId, username);
        return true;
    }

    @PutMapping("/id/{username}/{id}")
    public ResponseEntity<?> updateEntry(
            @PathVariable ObjectId id,
            @RequestBody JournalEntity newEntry,
            @PathVariable String username) {
        JournalEntity old = journalService.findById(id).orElse(null);
        if (old != null) {
            old.setTitle(!newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
            journalService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}