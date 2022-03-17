package com.untucapital.usuite.utg.service;


import com.untucapital.usuite.utg.model.Testimonial;
import com.untucapital.usuite.utg.repository.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TestimonialService {

    @Autowired
    private final TestimonialRepository testimonialRepository;

    public TestimonialService(TestimonialRepository testimonialRepository) {
        this.testimonialRepository = testimonialRepository;
    }

    //Save
    public void saveTestimonial(Testimonial testimonial){
        testimonialRepository.save(testimonial);
    }
    //Get
    public List<Testimonial> getTestimonials(){
        return testimonialRepository.findAll();
    }
    //Delete
    public  void deleteTestimonial(String id){
        testimonialRepository.deleteById(id);
    }
    //Get by id
    public Optional<Testimonial> getTestimonial(String id) {
        return testimonialRepository.findById(id);
    }
}
