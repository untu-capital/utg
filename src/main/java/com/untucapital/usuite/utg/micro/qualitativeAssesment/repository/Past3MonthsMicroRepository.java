package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.Past3MonthsMicro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Past3MonthsMicroRepository extends JpaRepository<Past3MonthsMicro, String> {
    List<Past3MonthsMicro> findAllByLoanId(String id);
}
