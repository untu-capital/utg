package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.ClientFeedback;
import com.untucapital.usuite.utg.repository.ClientFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@javax.transaction.Transactional
public class ClientFeedbackService {

    @Autowired
    private ClientFeedbackRepository clientFeedbackRepository;

    public ClientFeedback saveClientFeedback(ClientFeedback clientFeedback){
        return clientFeedbackRepository.save(clientFeedback);
    }

    @Transactional(value = "transactionManager")
    public List<ClientFeedback> getAllClientFeedback() {
        return clientFeedbackRepository.findAll();
    }
}
