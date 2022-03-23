package com.untucapital.usuite.utg.untuSite.service;


import com.untucapital.usuite.utg.untuSite.model.Testimonial;
import com.untucapital.usuite.utg.untuSite.repository.TestimonialRepository;
import com.untucapital.usuite.utg.untuSite.response.GlobalImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class TestimonialService {
    @Autowired
    private final GlobalImageService imageService;

    @Autowired
    private final TestimonialRepository testimonialRepository;

    public TestimonialService(GlobalImageService imageService, TestimonialRepository testimonialRepository) {
        this.imageService = imageService;
        this.testimonialRepository = testimonialRepository;
    }

    //List All Testimonials
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

    //Save Testimonial
    public String saveTestimonial(MultipartFile file, String username, String comment, String loanType) {
        Testimonial testimonial = new Testimonial();
        String imageUrl = imageService.saveFile(file);

            testimonial.setUsername(username);
            testimonial.setComment(comment);
            testimonial.setLoanType(loanType);
            testimonial.setImageUrl(imageUrl);

            testimonialRepository.save(testimonial);

            return imageUrl;


    }

}
