package com.untucapital.usuite.utg.service;


import com.untucapital.usuite.utg.model.clientsDatasets;
import com.untucapital.usuite.utg.repository.ClientDatasetsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClientDatasetsService {

    @Autowired
    private ClientDatasetsRepository ClientDatasetsRepository;

    public clientsDatasets saveclientsDatasets(clientsDatasets clientsDatasets){
        return ClientDatasetsRepository.save(clientsDatasets);
    }

    public List<clientsDatasets> getAllclientsDatasets() {
        return ClientDatasetsRepository.findAll();
    }


}
