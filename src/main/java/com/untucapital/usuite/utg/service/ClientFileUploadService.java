package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.ClientFileUpload;
import com.untucapital.usuite.utg.repository.ClientFileUploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class ClientFileUploadService {

    @Autowired
    ClientFileUploadRepository clientFileUploadRepository;

    public void save(ClientFileUpload clientFileUpload){
        clientFileUploadRepository.save(clientFileUpload);
    }

    public List<ClientFileUpload> getClientFileUpload(String userId){
        return clientFileUploadRepository.findClientFileUploadByUserId(userId);
    }
}
