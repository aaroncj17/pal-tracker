package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    List<TimeEntry> timeEntryList = new ArrayList<>();

    long timeEntryId = 1L;

    public TimeEntry create(TimeEntry timeEntry) {

        timeEntry.setId(timeEntryId++);

        timeEntryList.add(timeEntry);

        return timeEntry;
    }


    public TimeEntry find(long id) {


        for(TimeEntry timeEntry : timeEntryList)
        {
            if(timeEntry.getId() == id)
                return timeEntry;
        }
       return null;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        for(TimeEntry timeEntryObj : timeEntryList)
        {
            if(timeEntryObj.getId() == id) {
                timeEntryObj.setProjectId(timeEntry.getProjectId());
                timeEntryObj.setDate(timeEntry.getDate());
                timeEntryObj.setHours(timeEntry.getHours());
                timeEntryObj.setUserId(timeEntry.getUserId());
                return timeEntryObj;
            }
        }
        return null;
    }


    public void delete(long id) {
        int index = -1;
        for(TimeEntry timeEntry : timeEntryList)
        {
            if(timeEntry.getId() == id)
                index = timeEntryList.indexOf(timeEntry);
        }
        if(index >= 0){
            timeEntryList.remove(index);
        }

    }

    public List<TimeEntry> list() {

        return timeEntryList;
    }
}
