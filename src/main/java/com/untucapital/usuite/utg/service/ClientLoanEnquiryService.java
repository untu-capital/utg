package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Business;
import com.untucapital.usuite.utg.model.ClientLoanEnquiry;
import com.untucapital.usuite.utg.repository.BusinessRepository;
import com.untucapital.usuite.utg.repository.ClientLoanEnquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientLoanEnquiryService {

    @Autowired
    private ClientLoanEnquiryRepository clientLoanEnquiryRepository;

    public ClientLoanEnquiry saveClientLoanEnquiry(ClientLoanEnquiry clientLoanEnquiry){
        return clientLoanEnquiryRepository.save(clientLoanEnquiry);
    }

    @org.springframework.transaction.annotation.Transactional(value = "transactionManager")
    public List<ClientLoanEnquiry> getAllClientLoanEnquiries() {
        return clientLoanEnquiryRepository.findAll();
    }
}
