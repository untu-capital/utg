package com.untucapital.usuite.utg.untu_capital.repository;

import com.untucapital.usuite.utg.untu_capital.model.Faqs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqsRepository extends JpaRepository <Faqs, String> {

    //Find Faqs By id
    List<Faqs> findFaqsById(String id);
}
