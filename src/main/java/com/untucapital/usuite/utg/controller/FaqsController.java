package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Faqs;
import com.untucapital.usuite.utg.service.FaqsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/faqs")
public class FaqsController{

    @Autowired
    private final FaqsService faqsService;
    public FaqsController(FaqsService faqsService) {
        this.faqsService = faqsService;
    }
    //Save faqs
    @PostMapping("/save")
    public void saveFaqs(@RequestBody Faqs faqs){
        faqsService.saveFaqs(faqs);
    }
    //Get all faqs
    @GetMapping("/get")
    public List<Faqs> getaAllFaqs(){
        return faqsService.getAllFaqs();
    }
    //Get faqs by
    @GetMapping("/get/{faqsId}")
    public  List<Faqs> getFaqs(@PathVariable("faqsId") String id){
        return faqsService.getFaqsById(id);
    }
    //Delete faq by id
    @DeleteMapping("delete/{faqsId}")
    public void deleteFaqs(@PathVariable("faqsId") String id){
        faqsService.deleteFaqsById(id);
    }

}
