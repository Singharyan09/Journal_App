package com.Journal.journalApp.JournalService;

import com.Journal.journalApp.entity.JournalEntry;

import java.util.Collection;

public abstract class journalService {
    public void createJournal(JournalEntry Journal) {

    }

    public void updateJournal(long id, JournalEntry Journal) {

    }

    public void deleteJournal(long id) {

    }

    public Collection<JournalEntry> getJournal() {
        return null;
    }


    public boolean existsById(long id) {;
        return true;
    }

}
