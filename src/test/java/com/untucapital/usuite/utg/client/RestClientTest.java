//package com.untucapital.usuite.utg.client;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.untucapital.usuite.utg.model.transactions.PageItem;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//import static javax.management.Query.eq;
//import static jdk.jfr.internal.jfc.model.Constraint.any;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//class RestClientTest {
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private ObjectMapper objectMapper;
//
//    private RestClient myService;
//
//    @Before
//    public void setup() {
//        MockitoAnnotations.initMocks(this);
//        myService = new RestClient(restTemplate, objectMapper);
//    }
//    @Test
//    void getLoans() {
//        // Arrange
//        Long timestamp = 1633524862L;  // Sample timestamp
//        String loanString = "{ \"id\": 1, \"amount\": 1000.0 }";  // Sample loan JSON response
//
//        // Mock the behavior of restTemplate
//        ResponseEntity<String> responseEntity = ResponseEntity.ok(loanString);
//        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
//                .thenReturn(responseEntity);
//
//        // Mock the behavior of objectMapper
//        PageItem expectedLoan = new PageItem();
//        expectedLoan.setId(1L);
//        expectedLoan.setAmount(1000.0);
//        when(objectMapper.readValue(loanString, PageItem.class)).thenReturn(expectedLoan);
//
//        // Act
//        List<PageItem> loans = myService.getLoans(timestamp);
//
//        // Assert
//        assertEquals(1, loans.size());
//        PageItem actualLoan = loans.get(0);
//        assertEquals(expectedLoan.getId(), actualLoan.getId());
//        assertEquals(expectedLoan.getAmount(), actualLoan.getAmount(), 0.01);
//    }
//
//    }
//
//    @Test
//    void getTransactions() {
//    }
//
//    @Test
//    void getXFineractPlatformTenantId() {
//    }
//}