package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.controller.ClientLoanController;
import com.untucapital.usuite.utg.model.AccessLogs;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.repository.AccessLogsRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
@Repository
public class AccessLogsService {

    private final AccessLogsRepository accessLogsRepository;

    public AccessLogsService(AccessLogsRepository accessLogsRepository) {
        this.accessLogsRepository = accessLogsRepository;
    }


    public List<AccessLogs> getAccessLogs()  {
        return accessLogsRepository.findAll();
    }
}
