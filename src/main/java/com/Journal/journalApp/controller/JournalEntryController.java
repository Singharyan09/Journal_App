package com.Journal.journalApp.controller;


//import com.Journal.journalApp.JournalService.Journalserviceimp;
import com.Journal.journalApp.JournalService.journalService;
import com.Journal.journalApp.entity.JournalEntry;
import com.Journal.journalApp.exception.JournalNotfoundException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/Journal")
@CrossOrigin
public class JournalEntryController {

    @Autowired
    private journalService journalService;

    @PostMapping
    public ResponseEntity<JournalEntry> saveEntry(@RequestBody JournalEntry journalEntry){
        try {
            journalEntry.setDate(LocalDateTime.now());
            journalService.saveEntry(journalEntry);
            return new ResponseEntity<>(journalEntry, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }

    @GetMapping
    public List<JournalEntry> getAll(){
        return journalService.findAll();
    }


    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getById(@PathVariable ObjectId myId){
        Optional<JournalEntry> journalEntry = journalService.findById(myId);
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @DeleteMapping("id/{myId}")
    public boolean deleteById(@PathVariable ObjectId myId){
        journalService.delete(myId);
        return true;
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry){
        JournalEntry old = journalService.findById(id).orElse(null);
        if(old != null){
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty() ? newEntry.getTitle() : old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty() ? newEntry.getContent() : old.getContent());
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @PutMapping("{id}")
//    public String updateEntry(@PathVariable("id") String id  , @RequestBody JournalEntry journalEntry){
//        if(journalService.existsById(id)){
//            journalService.updateJournal(id,journalEntry);
//            return  "Journal Updated";
//        }
//        else throw new JournalNotfoundException();
//    }
//
//    @DeleteMapping
//    public boolean deleteEntry(@RequestBody JournalEntry journalEntry){
//        journalService.deleteJournal(journalEntry.getId());
//        return false;
//    }


}

//controller -- > service -- > repository


// for regenerated instance
//    @GetMapping
//    public List<JournalEntry> getAll(){
//        return new ArrayList<JournalEntry>(journalService.getJournal());
//    }