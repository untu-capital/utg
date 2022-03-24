package com.untucapital.usuite.utg.untu_capital.service;

import com.untucapital.usuite.utg.untu_capital.model.Faqs;
import com.untucapital.usuite.utg.untu_capital.repository.FaqsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FaqsService {

    private final FaqsRepository faqsRepository;

    @Autowired
    public FaqsService(FaqsRepository faqsRepository) {
        this.faqsRepository = faqsRepository;
    }
    //Save faqs
    public void saveFaqs(Faqs faqs){
        faqsRepository.save( faqs);
    }
    //Get all faqs
    public List<Faqs> getAllFaqs(){
        return faqsRepository.findAll();
    }
    //Get faqs by id
    public List<Faqs> getFaqsById(String id){
        return faqsRepository.findFaqsById(id);
    }
    //Delete faq by id
    public void deleteFaqsById(String id){
        faqsRepository.deleteAllById(Collections.singleton(id));
    }
}
