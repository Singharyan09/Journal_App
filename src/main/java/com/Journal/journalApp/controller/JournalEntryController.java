package com.Journal.journalApp.controller;


import com.Journal.journalApp.JournalService.Journalserviceimp;
import com.Journal.journalApp.JournalService.journalService;
import com.Journal.journalApp.entity.JournalEntry;
import com.Journal.journalApp.exception.JournalNotfoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Journal")
public class JournalEntryController {

    @Autowired
    journalService journalService;

    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<JournalEntry>(journalService.getJournal());
    }

    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry journalEntry){
        journalService.createJournal(journalEntry);
        return true;
    }


    @PutMapping("{id}")
    public String updateEntry(@PathVariable("id") long id  , @RequestBody JournalEntry journalEntry){
        if(journalService.existsById(id)){
            journalService.updateJournal(id,journalEntry);
            return  "Journal Updated";
        }
        else throw new JournalNotfoundException();
    }

    @DeleteMapping
    public boolean deleteEntry(@RequestBody JournalEntry journalEntry){
        journalService.deleteJournal(journalEntry.getId());
        return false;
    }


}
