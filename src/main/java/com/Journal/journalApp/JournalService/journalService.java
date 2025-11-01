package com.Journal.journalApp.JournalService;

import com.Journal.journalApp.entity.JournalEntry;
import com.Journal.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public  class journalService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> findAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void delete(ObjectId id) {
        journalEntryRepository.deleteById(id);
    }


//    public void createJournal(JournalEntry Journal) {
//    }
//    public void updateJournal(String id, JournalEntry Journal) {
//    }
//    public void deleteJournal(String id) {
//    }
//    public Collection<JournalEntry> getJournal() {
//        return null;
//    }
//    public boolean existsById(String id) {;
//        return true;
//    }

}
