package com.untucapital.usuite.utg.controller.settlementAccounts;


import com.untucapital.usuite.utg.service.settlementAccounts.SettlementAccountsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("settlements/")
public class SettlementAccountsController {

    private final SettlementAccountsService settlementAccountsService;

    @GetMapping("verifyToken/{clientId}/{token}")
    public boolean verifToken(@PathVariable("clientId") String clientId, @PathVariable("token") String token ){

        return settlementAccountsService.verifySettlementAccountsToken(clientId, token);

    }


}
