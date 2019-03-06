package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeEntryController {
    private final TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    public ResponseEntity<TimeEntry> create(TimeEntry timeEntryToCreate) {
        return new ResponseEntity(timeEntryRepository.create(timeEntryToCreate),
                HttpStatus.CREATED);
    }

    public ResponseEntity<TimeEntry> read(long timeEntryId) {
        TimeEntry timeEntry = timeEntryRepository.find(timeEntryId);
        if(timeEntry == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(timeEntry);
        }
    }

    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> timeEntries = timeEntryRepository.list();
        return ResponseEntity.ok(timeEntries);
    }

    public ResponseEntity update(long timeEntryId, TimeEntry timeEntry) {
        TimeEntry timeEntry1 = timeEntryRepository.update(timeEntryId, timeEntry);
        if(timeEntry1 == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(timeEntry1);
        }
    }

    public ResponseEntity<TimeEntry> delete(long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
}
