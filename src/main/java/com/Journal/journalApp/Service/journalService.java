
package com.Journal.journalApp.Service;
import com.Journal.journalApp.Model.JournalEntity;
import com.Journal.journalApp.Model.UserEntity;
import com.Journal.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public  class journalService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private  UserService userService;

    public void saveEntry(JournalEntity journalEntity, String username){
        UserEntity userEntity = userService.findByUsername(username);
        journalEntity.setDate(LocalDateTime.now());
        JournalEntity saved = journalEntryRepository.save(journalEntity);
        userEntity.getJournalEntries().add(saved);
        userService.saveEntry(userEntity);
    }

    public void saveEntry(JournalEntity journalEntity){
        journalEntryRepository.save(journalEntity);
    }

    public List<JournalEntity> findAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntity> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void delete(ObjectId id, String username) {
        UserEntity  userupd = userService.findByUsername(username);
        userupd.getJournalEntries().removeIf( x -> x.getId().equals(id));
        userService.saveEntry(userupd);
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
