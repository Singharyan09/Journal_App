package com.Journal.journalApp.JournalService;

import com.Journal.journalApp.entity.JournalEntry;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Journalserviceimp {

    @Service
    public static class ProductServiceImpl extends journalService {

        private final Map<Long, JournalEntry> journalEntryMap = new HashMap<Long, JournalEntry>();

        @Override
        public void createJournal(JournalEntry journal) {
            journalEntryMap.put(journal.getId(),journal);
        }

        @Override
        public void updateJournal(long id,JournalEntry journal) {
            journalEntryMap.remove(id);
            journal.setId(id);
            journalEntryMap.put(id, journal);

        }

        @Override
        public void deleteJournal(long id) {
            journalEntryMap.remove(id);
        }

        @Override
        public Collection<JournalEntry> getJournal() {
            return journalEntryMap.values();
        }

        @Override
        public  boolean existsById(long id) {
            return journalEntryMap.containsKey(id);
        }


    }


}
