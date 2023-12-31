package com.untucapital.usuite.utg.controller;
import com.untucapital.usuite.utg.model.Business;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.Meetings;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.MeetingsRepository;
import com.untucapital.usuite.utg.service.AbstractService;
import com.untucapital.usuite.utg.service.MeetingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "meetings")
public class MeetingsController extends AbstractController<Meetings> {
    @Autowired
    MeetingsRepository meetingsRepository;

    private static final Logger log = LoggerFactory.getLogger(MeetingsController.class);

    private final MeetingsService meetingsService;

    public MeetingsController(MeetingsService meetingsService) {
        this.meetingsService = meetingsService;
    }

    //build save branch REST API
    @PostMapping("/addMeetings")
    public void add(@RequestBody Meetings meetings) {
        meetingsService.saveMeetings(meetings);
    }

    //Get collateral by Id
    @GetMapping("/collateralById/{id}")
    public ResponseEntity<Meetings> getCollateralById(@PathVariable("id") String id) {
        return new ResponseEntity<Meetings>(meetingsRepository.findMeetingsById(id), HttpStatus.OK);
    }

    //Get collateral by loanId
    @GetMapping("/collateralByLoanId/{loanId}")
    public List<Meetings> getCollateralByLoanId(@PathVariable("loanId") String loanId) {
        return meetingsRepository.findMeetingsByLoanId(loanId);
    }



//    @DeleteMapping("/deleteBranch/{id}")
//    public void delete(@PathVariable String id) {
//
//        meetingsService.deleteMeeting(id);

    @Override
    protected AbstractService<Meetings> getService() {
        return meetingsService;
    }
}

