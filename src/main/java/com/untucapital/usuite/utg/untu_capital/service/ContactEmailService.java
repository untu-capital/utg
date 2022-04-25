package com.untucapital.usuite.utg.untu_capital.service;

import com.untucapital.usuite.utg.untu_capital.model.ContactEmail;
import com.untucapital.usuite.utg.untu_capital.repository.ContactEmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactEmailService {
    @Autowired
    private final ContactEmailRepository contactEmailRepository;

    public ContactEmailService(ContactEmailRepository contactEmailRepository) {
        this.contactEmailRepository = contactEmailRepository;
    }
    //New Email
    public void save(String name, String email,String message, String industry, String status) {
        ContactEmail contactEmail=new ContactEmail();
        contactEmail.setName(name);
        contactEmail.setEmail(email);
        contactEmail.setMessage(message);
        contactEmail.setIndustry(industry);
        contactEmail.setStatus(Boolean.parseBoolean(status));

        contactEmailRepository.save(contactEmail);
    }
    //Delete Email
    public  void deleteById(String id){
        contactEmailRepository.deleteById(id);
    }
    //Find All emails
    public List<ContactEmail> findAll(){
        return contactEmailRepository.findAll();
    }




}
