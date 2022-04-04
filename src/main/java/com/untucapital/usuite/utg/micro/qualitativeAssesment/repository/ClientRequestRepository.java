package com.untucapital.usuite.utg.micro.qualitativeAssesment.repository;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.ClientRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRequestRepository extends JpaRepository<ClientRequest, String> {
    List<ClientRequest> findAllByLoanId(String id);
}
