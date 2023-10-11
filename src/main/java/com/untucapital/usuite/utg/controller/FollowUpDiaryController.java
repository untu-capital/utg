package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.FollowUpDiary;
import com.untucapital.usuite.utg.repository.FollowUpDiaryRepository;
import com.untucapital.usuite.utg.service.FollowUpDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/followUpDiary")
public class FollowUpDiaryController {

    private final FollowUpDiaryService followUpDiaryService;

    @Autowired
    public FollowUpDiaryController(FollowUpDiaryService followUpDiaryService) {
        this.followUpDiaryService = followUpDiaryService;
    }

    @PostMapping
    public ResponseEntity<FollowUpDiary> createFollowUpDiary(@RequestBody FollowUpDiary followUpDiary) {
        FollowUpDiary createdFollowUpDiary = followUpDiaryService.createFollowUpDiary(followUpDiary);
        return new ResponseEntity<>(createdFollowUpDiary, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FollowUpDiary> updateFollowUpDiary(
            @PathVariable("id") String id, @RequestBody FollowUpDiary followUpDiary) {
        FollowUpDiary updatedFollowUpDiary = followUpDiaryService.updateFollowUpDiary(id, followUpDiary);
        if (updatedFollowUpDiary != null) {
            return new ResponseEntity<>(updatedFollowUpDiary, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<FollowUpDiary> getFollowUpDiaryById(@PathVariable("id") String id) {
        FollowUpDiary followUpDiary = followUpDiaryService.getFollowUpDiaryById(id);
        if (followUpDiary != null) {
            return new ResponseEntity<>(followUpDiary, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/byClientId/{clientid}")
    public ResponseEntity<List<FollowUpDiary>> getFollowUpDiaryByClientid(@PathVariable("clientid") String clientid) {
        // FollowUpDiary followUpDiary = (FollowUpDiary) followUpDiaryService.getFollowUpDiaryByClientid(clientid);
        return new ResponseEntity<List<FollowUpDiary>>(followUpDiaryService.getFollowUpDiaryByClientid(clientid), HttpStatus.OK);

    }
    
    @GetMapping
    public ResponseEntity<List<FollowUpDiary>> getAllFollowUpDiaries() {
        List<FollowUpDiary> followUpDiaryList = followUpDiaryService.getAllFollowUpDiaries();
        return new ResponseEntity<>(followUpDiaryList, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFollowUpDiary(@PathVariable("id") String id) {
        followUpDiaryService.deleteFollowUpDiary(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
