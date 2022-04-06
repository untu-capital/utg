package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.Past3WorkingDaysMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Past3WorkingDaysMicroRepository extends JpaRepository<Past3WorkingDaysMicro, String> {
    List<Past3WorkingDaysMicro> findAllByLoanId(String id);
}
