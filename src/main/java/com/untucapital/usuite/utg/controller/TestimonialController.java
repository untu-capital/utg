package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.Testimonial;
import com.untucapital.usuite.utg.service.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "testimonials")
public class TestimonialController {
    @Autowired
    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }
    //save
    @PostMapping("/save")
    public  void saveTestimonial(@RequestBody Testimonial testimonial){
        testimonialService.saveTestimonial(testimonial);
    }
    //get
    @GetMapping("/get")
    public List<Testimonial> getTestimonials(){
        return testimonialService.getTestimonials();
    }
    //delete
    @DeleteMapping("/delete/{testimonialId}")
    public void deleteTestimonial(@PathVariable("testimonialId") String id){
        testimonialService.deleteTestimonial(id);
    }
    //Get by id
    @GetMapping("/get/{testimonialId}")
    public Optional<Testimonial> getTestimonial(@PathVariable("testimonialId") String id){
        return testimonialService.getTestimonial(id);
    }

}
