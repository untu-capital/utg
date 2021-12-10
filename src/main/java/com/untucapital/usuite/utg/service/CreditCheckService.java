package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.exception.UntuSuiteException;
import com.untucapital.usuite.utg.integration.fcbintservice.FCBIntegrationService;
import com.untucapital.usuite.utg.model.ClientLoan;
import com.untucapital.usuite.utg.model.fcb.Response;
import com.untucapital.usuite.utg.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chirinda Nyasha Dell - 9/12/2021
 */

@Service
public class CreditCheckService {

    private static final Logger log = LoggerFactory.getLogger(CreditCheckService.class);

    private final FCBIntegrationService fcbIntegrationService;
    private final ClientRepository clientRepository;

    @Autowired
    public CreditCheckService(FCBIntegrationService fcbIntegrationService, ClientRepository clientRepository) {
        this.fcbIntegrationService = fcbIntegrationService;
        this.clientRepository = clientRepository;
    }

    public ClientLoan fetchFCBCreditStatus(ClientLoan clientLoan) {
        log.info("Fetching FCB Credit Status for Client: {}, ID:{}", clientLoan.getFirstname() + clientLoan.getLastname(), clientLoan.getId_number());

        clientLoan.setStatus("PENDING");

        final Response creditResponse = fcbIntegrationService.performSearch(clientLoan)
                .orElseThrow(() -> new UntuSuiteException("Credit check failed for the Loan Application"));

        String creditStatus = creditResponse.getReport().get(0).getStatus();
        Integer creditScore = creditResponse.getReport().get(0).getScore();

        clientLoan.setFcbStatus(creditStatus);
        clientLoan.setFcbScore(creditScore);

        if (creditStatus.equals("GREEN") || creditStatus.equals("GOOD")) {
            clientLoan.setStatus("ACCEPTED");
        } else if (creditStatus.equals("FAIR") || creditStatus.equals("ADVERSE") || creditStatus.equals("PEP")) {
            clientLoan.setStatus("REJECTED");
        }
        return clientLoan;
    }

    @Scheduled(initialDelayString = "${fixed-delay.ms}", fixedDelayString = "${fixed-delay.ms}")
    protected void pollCreditChecks() {
        log.debug("Polling Inconclusive Credit Checks");
        long startTime = System.currentTimeMillis();

        final List<ClientLoan> clientLoans = clientRepository.findAll();

        List<ClientLoan> pendingClientCreditChecks = clientLoans
                .stream()
                .filter(cl -> "INCONCLUSIVE".equals(cl.getFcbStatus()) && "PENDING".equals(cl.getStatus()))
                .collect(Collectors.toList());

        pendingClientCreditChecks.forEach(this::fetchFCBCreditStatus);

        long finishTime = System.currentTimeMillis();
        if (!pendingClientCreditChecks.isEmpty())
            log.debug("Completed Poll of Inconclusive Checks: {}. Poll took {}ms", LocalDateTime.now(), finishTime - startTime);
    }
}
