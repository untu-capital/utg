package com.untucapital.usuite.utg.untuSite.repository;

import com.untucapital.usuite.utg.untuSite.model.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, String> {

}
