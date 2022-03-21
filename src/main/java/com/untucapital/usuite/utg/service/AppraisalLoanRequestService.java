package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.AppraisalLoanRequest;
import com.untucapital.usuite.utg.model.Business;
import com.untucapital.usuite.utg.repository.AppraisalLoanRequestRepository;
import com.untucapital.usuite.utg.repository.BusinessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class AppraisalLoanRequestService {
    @Autowired
    private AppraisalLoanRequestRepository appraisalLoanRequestRepository;
    public List<AppraisalLoanRequest> listAllAppraisalLoanRequest() {
        return appraisalLoanRequestRepository.findAll();
    }

    public void saveAppraisalLoanRequest(AppraisalLoanRequest appraisalLoanRequest) {
        appraisalLoanRequestRepository.save(appraisalLoanRequest);
    }

    public AppraisalLoanRequest getAppraisalLoanRequest(Integer id) {
        return appraisalLoanRequestRepository.findById(id).get();
    }

    public void deleteAppraisalLoanRequest(Integer id) {
        appraisalLoanRequestRepository.deleteById(id);
    }
}
