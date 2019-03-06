package io.pivotal.pal.tracker;

import java.util.*;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> inMemoryTimeEntryRepository;
    private long timeEntryId = 0L;

    public InMemoryTimeEntryRepository() {
        inMemoryTimeEntryRepository = new HashMap<>();
    }

    public TimeEntry create(TimeEntry timeEntry) {
        long timeEntryId = getNextId();

        TimeEntry createdTimeEntry = new TimeEntry(timeEntryId,
                timeEntry.getProjectId(), timeEntry.getUserId(),
                timeEntry.getDate(), timeEntry.getHours());

        inMemoryTimeEntryRepository.put(timeEntryId, createdTimeEntry);
        return createdTimeEntry;
    }

    private long getNextId() {
        return ++timeEntryId;
    }

    @Override
    public TimeEntry find(long id) {
        return inMemoryTimeEntryRepository.get(id);
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(
                (inMemoryTimeEntryRepository.values()));
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {

        if(inMemoryTimeEntryRepository.get(id) != null) {
            TimeEntry updatedTimeEntry = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(),
                    timeEntry.getDate(), timeEntry.getHours());
            inMemoryTimeEntryRepository.put(id, updatedTimeEntry);
            return updatedTimeEntry;
        } else return null;

    }

    @Override
    public void delete(long id) {

        inMemoryTimeEntryRepository.remove(id);
    }
}
