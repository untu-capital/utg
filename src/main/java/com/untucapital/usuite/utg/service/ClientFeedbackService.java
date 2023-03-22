package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.ClientFeedback;
import com.untucapital.usuite.utg.repository.ClientFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientFeedbackService {

    @Autowired
    private ClientFeedbackRepository clientFeedbackRepository;

    public ClientFeedback saveClientFeedback(ClientFeedback clientFeedback){
        return clientFeedbackRepository.save(clientFeedback);
    }

    public List<ClientFeedback> getAllClientFeedback() {
        return clientFeedbackRepository.findAll();
    }
}
