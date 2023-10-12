package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.LeadStatus;
import com.untucapital.usuite.utg.model.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeadStatusRepository extends JpaRepository<LeadStatus, String> {

    LeadStatus findLeadStatusById(String leadId);
}
