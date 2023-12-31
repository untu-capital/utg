package com.untucapital.usuite.utg.controller;

import com.untucapital.usuite.utg.model.LongTermCreditHistory;
import com.untucapital.usuite.utg.model.MitigatingRisk;
import com.untucapital.usuite.utg.service.MitigatingRiskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mitigating_risk")
public class MitigatingRiskController {
    @Autowired
    MitigatingRiskService mitigatingRiskService;

    private static final Logger log = LoggerFactory.getLogger(MitigatingRiskController.class);

    @GetMapping("/get/{loanId}")
    public List<MitigatingRisk> getByLoanId(@PathVariable("loanId") String loanId) {
        return mitigatingRiskService.getMitigatingRiskByLoanId(loanId);
    }

    @PostMapping("/save")
    public void add(@RequestBody MitigatingRisk mitigatingRisk) {
        mitigatingRiskService.saveMitigatingRisk(mitigatingRisk);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable String id) {
        mitigatingRiskService.deleteMitigatingRisk(id);
    }
}
