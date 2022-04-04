package com.untucapital.usuite.utg.untu_capital.controller;

import com.untucapital.usuite.utg.untu_capital.model.Testimonial;
import com.untucapital.usuite.utg.untu_capital.response.GlobalImageResponse;
import com.untucapital.usuite.utg.untu_capital.service.TestimonialService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "testimonials")
public class TestimonialController {

    private final TestimonialService testimonialService;

    public TestimonialController(TestimonialService testimonialService) {
        this.testimonialService = testimonialService;
    }

    @PostMapping("/save")
    public ResponseEntity<GlobalImageResponse> saveTestimonial(
            @RequestParam(name = "file", required = false) MultipartFile file,
            @RequestParam("username") String username,
            @RequestParam("comment") String comment,
            @RequestParam("loanType") String loanType
    ){

        String fileName = testimonialService.saveTestimonial(file, username, comment, loanType);
        GlobalImageResponse uploadResponse = new GlobalImageResponse(fileName);
        return ResponseEntity.ok().body(uploadResponse);
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
