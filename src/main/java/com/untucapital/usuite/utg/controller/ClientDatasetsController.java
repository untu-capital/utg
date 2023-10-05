package com.untucapital.usuite.utg.controller;

// import com.untucapital.usuite.utg.model.ClientFeedback;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.ClientLoanEnquiry;
import com.untucapital.usuite.utg.model.clientsDatasets;
import com.untucapital.usuite.utg.repository.ClientDatasetsRepository;
// import com.untucapital.usuite.utg.repository.ClientFeedbackRepository;
import com.untucapital.usuite.utg.repository.ClientLoanEnquiryRepository;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.service.ClientDatasetsService;
// import com.untucapital.usuite.utg.service.ClientFeedbackService;
import com.untucapital.usuite.utg.service.ClientLoanApplication;
import com.untucapital.usuite.utg.service.ClientLoanEnquiryService;
import com.untucapital.usuite.utg.utils.EmailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "credit_application_client_datasets")
public class ClientDatasetsController {


    @Autowired
    ClientDatasetsRepository clientDatasetsRepository;

    @Autowired
    ClientDatasetsService ClientDatasetsService;

    private static final Logger log = LoggerFactory.getLogger(ClientDatasetsController.class);

    //build save loan REST API
    @PostMapping
    public ResponseEntity<clientsDatasets> saveClientFeedback(@RequestBody clientsDatasets clientsDatasets) {
        log.info(String.valueOf(clientsDatasets));
        return new ResponseEntity<clientsDatasets>(ClientDatasetsService.saveclientsDatasets(clientsDatasets), HttpStatus.CREATED);
    }

    //build get all loan applications REST API
    @GetMapping
    public List<clientsDatasets> getAllclientsDatasets() {
        return ClientDatasetsService.getAllclientsDatasets();

    }

    //build get clientLoan by ID REST API
    @GetMapping("{id}")
    public ResponseEntity<clientsDatasets> getclientsDatasetsById(@PathVariable("id") Long id) {
        return new ResponseEntity<clientsDatasets>( clientDatasetsRepository.findClientsDatasetsById(id), HttpStatus.OK);
    }
    //build get clientLoan by Branch REST API
    @GetMapping("/branchName/{branch}")

    public List<clientsDatasets> getClientsByBranch( @PathVariable ("branch") String branch) {
        return clientDatasetsRepository.findByBranch(branch);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateclientsDatasets(@PathVariable Long id, @RequestBody clientsDatasets clientsDatasets){
        clientsDatasets updatedclientsDatasets = clientDatasetsRepository.findClientsDatasetsById(id);
        updatedclientsDatasets.setFirstName(clientsDatasets.getFirstName());
        updatedclientsDatasets.setLastName(clientsDatasets.getLastName());
        updatedclientsDatasets.setBusinessSector(clientsDatasets.getBusinessSector());
        updatedclientsDatasets.setPhoneNumber(clientsDatasets.getPhoneNumber());
        updatedclientsDatasets.setBranch(clientsDatasets.getBranch());
        updatedclientsDatasets.setNationalId(clientsDatasets.getNationalId());
        updatedclientsDatasets.setHomeAddress(clientsDatasets.getHomeAddress());
        updatedclientsDatasets.setCreditRating(clientsDatasets.getCreditRating());
        updatedclientsDatasets.setLoanAmount(clientsDatasets.getLoanAmount());
        updatedclientsDatasets.setLoanProduct(clientsDatasets.getLoanProduct());
        updatedclientsDatasets.setOrigin(clientsDatasets.getOrigin());
        updatedclientsDatasets.setGender(clientsDatasets.getGender());
        updatedclientsDatasets.setDateOfBirth(clientsDatasets.getDateOfBirth());


        clientDatasetsRepository.save(updatedclientsDatasets);
        return new ResponseEntity<String>("Client Dataset successfully updated.", HttpStatus.OK);
    }


    //build get clientLoan by ID REST API


}




