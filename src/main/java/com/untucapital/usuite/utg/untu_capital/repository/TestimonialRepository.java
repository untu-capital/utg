package com.untucapital.usuite.utg.untu_capital.repository;

import com.untucapital.usuite.utg.untu_capital.model.Testimonial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TestimonialRepository extends JpaRepository<Testimonial, String> {

}
