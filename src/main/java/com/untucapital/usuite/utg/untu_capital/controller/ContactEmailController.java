package com.untucapital.usuite.utg.untu_capital.controller;

import com.untucapital.usuite.utg.untu_capital.model.ContactEmail;
import com.untucapital.usuite.utg.untu_capital.repository.ContactEmailRepository;
import com.untucapital.usuite.utg.untu_capital.service.ContactEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "contactEmail")
public class ContactEmailController {
    @Autowired
    private final ContactEmailService contactEmailService;
    @Autowired
    private final ContactEmailRepository contactEmailRepository;
    public ContactEmailController(ContactEmailService contactEmailService, ContactEmailRepository contactEmailRepository) {
        this.contactEmailService = contactEmailService;
        this.contactEmailRepository = contactEmailRepository;
    }
    //New Email
    @PostMapping("/save")
    public void save(@RequestParam(name = "name", required = false) String name,
                     @RequestParam(name = "email", required = false) String email,
                     @RequestParam(name = "message", required = false)String message,
                     @RequestParam(name = "industry", required = false)String industry,
                     @RequestParam(name = "status", required = false) String status
                     ){
        contactEmailService.save(name,email,message,industry,status);
    }
    //Delete by id
    @DeleteMapping("/delete/{emailId}")
    public void deleteById(@PathVariable("emailId") String id){
        contactEmailService.deleteById(id);
    }
    //Find All Emails
    @GetMapping("/get")
    public List<ContactEmail> findAll(){
        return contactEmailService.findAll();
    }
    //Change Status to seen
    @PutMapping("/status/{emailId}")
    public ResponseEntity<String> update(@PathVariable("emailId") String id,
                        @RequestParam(name = "status", required = false) String status
    ){
        ContactEmail contactEmail = contactEmailRepository.getById(id);
        contactEmail.setStatus(Boolean.parseBoolean(status));

        contactEmailRepository.save(contactEmail);

        return  new ResponseEntity<String>("Team Management Successfully Updated", HttpStatus.OK);

    }
}
