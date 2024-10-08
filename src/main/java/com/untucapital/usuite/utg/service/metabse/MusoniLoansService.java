package com.untucapital.usuite.utg.service.metabse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.untucapital.usuite.utg.client.RestClient;
import com.untucapital.usuite.utg.commons.AppConstants;
import com.untucapital.usuite.utg.dto.DisbursedLoans;
import com.untucapital.usuite.utg.dto.*;
import com.untucapital.usuite.utg.dto.client.Client;
import com.untucapital.usuite.utg.dto.client.ClientFeesResponse;
import com.untucapital.usuite.utg.dto.client.ViewClientLoansResponse;
import com.untucapital.usuite.utg.dto.client.loan.LoanAccount;
import com.untucapital.usuite.utg.dto.client.repaymentSchedule.ClientStatementResponse;
import com.untucapital.usuite.utg.dto.client.repaymentSchedule.NextInstalmentResponse;
import com.untucapital.usuite.utg.dto.loans.RepaymentSchedule;
import com.untucapital.usuite.utg.dto.loans.Result;
import com.untucapital.usuite.utg.dto.loans.*;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.ClientAccounts;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.PageItems;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.SavingsAccountLoans;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.SettlementAccountResponse;
import com.untucapital.usuite.utg.dto.musoni.savingsaccounts.transactions.SavingsAccountsTransactions;
import com.untucapital.usuite.utg.dto.pastel.PastelTransReq;
import com.untucapital.usuite.utg.dto.request.PostGLRequestDTO;
import com.untucapital.usuite.utg.entity.PostGl;
import com.untucapital.usuite.utg.entity.res.PostGlResponseDTO;
import com.untucapital.usuite.utg.exception.EmptyException;
import com.untucapital.usuite.utg.exception.SettlementAccountNotFoundException;
import com.untucapital.usuite.utg.exception.SmsException;
import com.untucapital.usuite.utg.model.MusoniClient;
import com.untucapital.usuite.utg.model.metabase.MusoniLoans;
import com.untucapital.usuite.utg.model.settlements.SettlementAccountsTokens;
import com.untucapital.usuite.utg.model.transactions.Loans;
import com.untucapital.usuite.utg.model.transactions.PageItem;
import com.untucapital.usuite.utg.model.transactions.TransactionInfo;
import com.untucapital.usuite.utg.model.transactions.Transactions;
import com.untucapital.usuite.utg.model.transactions.interim.dto.SavingsTransactionDTO;
import com.untucapital.usuite.utg.model.transactions.interim.dto.TransactionDTO;
import com.untucapital.usuite.utg.model.transactions.interim.dto.TransactionTypeDTO;
import com.untucapital.usuite.utg.processor.MusoniProcessor;
import com.untucapital.usuite.utg.repository.MusoniRepository;
import com.untucapital.usuite.utg.repository.metabase.MusoniLoansRepository;
import com.untucapital.usuite.utg.repository.settlementsAccounts.SettlementAccountsTokensRepository;
import com.untucapital.usuite.utg.service.PostGlService;
import com.untucapital.usuite.utg.service.RoleService;
import com.untucapital.usuite.utg.service.SmsService;
import com.untucapital.usuite.utg.utils.MusoniUtils;
import com.untucapital.usuite.utg.utils.RandomNumUtils;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;


@Service
@Data
@RequiredArgsConstructor
@Configuration
public class MusoniLoansService {


    @Value("${musoni.url}")
    private String musoniUrl;
    @Value("${musoni.username}")
    private String musoniUsername;
    @Value("${musoni.password}")
    private String musoniPassword;
    @Value("${musoni.X_FINERACT_PLATFORM_TENANTID}")
    private String musoniTenantId;
    @Value("${musoni.X_API_KEY}")
    private String musoniApiKey;

    @Autowired
    private final RestTemplate restTemplate;

    private final RestClient restClient;

    private final MusoniLoansRepository musoniLoansRepository;

    private static final Logger log = LoggerFactory.getLogger(RoleService.class);


    public MusoniLoans saveMusoniLoansToUtg() {

        MusoniLoans musoniLoans = restClient.saveMusoniLoansToUtg();

        return musoniLoansRepository.save(musoniLoans);
    }

}

