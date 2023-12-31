package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.exception.UntuSuiteException;
import com.untucapital.usuite.utg.integration.fcbintservice.FCBIntegrationService;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.fcb.Response;
import com.untucapital.usuite.utg.repository.ClientRepository;
import com.untucapital.usuite.utg.repository.FCBResponseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditCheckService {

    private static final Logger log = LoggerFactory.getLogger(CreditCheckService.class);

    private final FCBIntegrationService fcbIntegrationService;
    private final ClientRepository clientRepository;
    private final FCBResponseRepository fcbResponseRepository;

    @Autowired
    public CreditCheckService(FCBIntegrationService fcbIntegrationService, ClientRepository clientRepository, FCBResponseRepository fcbResponseRepository) {
        this.fcbIntegrationService = fcbIntegrationService;
        this.clientRepository = clientRepository;
        this.fcbResponseRepository = fcbResponseRepository;
    }

    public ClientLoan fetchFCBCreditStatus(ClientLoan clientLoan) {
        log.info("Fetching FCB Credit Status for Client: {}, ID:{}", clientLoan.getFirstName() + clientLoan.getLastName(), clientLoan.getIdNumber());

        //clientLoan.setLoanStatus("PENDING");

        final Response creditResponse = fcbIntegrationService.performSearch(clientLoan)
                .orElseThrow(() -> new UntuSuiteException("Credit check failed for the Loan Application"));

        Response savedResponse = fcbResponseRepository.save(creditResponse);

        String creditStatus = creditResponse.getReport().get(0).getStatus();
        Integer creditScore = creditResponse.getReport().get(0).getScore();

        clientLoan.setFcbResponse(savedResponse);
        clientLoan.setFcbStatus(creditStatus);
        clientLoan.setFcbScore(creditScore);

        //if (creditStatus.equals("GREEN") || creditStatus.equals("GOOD")) {
          //  clientLoan.setLoanStatus("ACCEPTED");
        //} else if (creditStatus.equals("FAIR") || creditStatus.equals("ADVERSE") || creditStatus.equals("PEP")) {
            //clientLoan.setLoanStatus("REJECTED");
       // }
        return clientLoan;
    }

//    @Scheduled(initialDelayString = "${fixed-delay.ms}", fixedDelayString = "${fixed-delay.ms}")
    protected void pollCreditChecks() {
        log.debug("Polling Inconclusive Credit Checks");
        long startTime = System.currentTimeMillis();

        final List<ClientLoan> clientLoans = clientRepository.findAll();

        List<ClientLoan> pendingClientCreditChecks = clientLoans
                .stream()
                .filter(cl -> "PENDING".equals(cl.getLoanStatus()))
                .collect(Collectors.toList());

        pendingClientCreditChecks.forEach(this::fetchFCBCreditStatus);

        long finishTime = System.currentTimeMillis();
        if (!pendingClientCreditChecks.isEmpty())
            log.debug("Completed Poll of Inconclusive Checks: {}. Poll took {}ms", LocalDateTime.now(), finishTime - startTime);
    }
}
