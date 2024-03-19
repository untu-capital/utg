package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.dto.LoansPipelineDTO;
import com.untucapital.usuite.utg.model.LoansPipeline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoansPipelineRepository extends JpaRepository<LoansPipeline, Long> {
    int countByLoanOfficerAndLoanStatus(String loanOfficer, String loanStatus);

    List<LoansPipeline> findLoansPipelineByLoanOfficer(String userId);

    @Query(nativeQuery = true, value = "SELECT IFNULL(`branch_name`, 'Total') AS `branch_name`, " +
            "(SELECT COUNT(*) FROM loanspipeline lp WHERE lp.branch_name = main.branch_name) AS 'Caseloads', " +
            "SUM(CASE WHEN `loan_status` = 'Disbursement' THEN `sought_loan` ELSE 0 END) AS 'Disbursements', " +
            "SUM(CASE WHEN `loan_status` = 'Pending Disbursement' THEN `sought_loan` ELSE 0 END) AS 'Pending Disbursements', " +
            "SUM(CASE WHEN `loan_status` = 'Prospect' THEN `sought_loan` ELSE 0 END) AS 'Prospects', " +
            "SUM(CASE WHEN `loan_status` = 'Assessment' THEN `sought_loan` ELSE 0 END) AS 'Assessments', " +
            "SUM(CASE WHEN `loan_status` != 'Disbursement' THEN `sought_loan` ELSE 0 END) AS 'Total Pipeline', " +
            "SUM(CASE WHEN `repeat_client` = 'New' THEN 1 ELSE 0 END) AS 'New', " +
            "SUM(CASE WHEN `repeat_client` = 'Repeat' THEN 1 ELSE 0 END) AS 'Repeat' " +
            "FROM loanspipeline main " +
            "GROUP BY `branch_name` WITH ROLLUP")
    List<Object []> getLoanPipelineSummary();

    @Query(nativeQuery = true, value = "SELECT IFNULL(`branch_name`, 'Total') AS 'Branch Name', `loan_officer` AS 'Loan Officer', SUM(CASE WHEN `loan_status` = 'Disbursement' THEN 1 ELSE 0 END) AS 'Disbursed', SUM(CASE WHEN `loan_status` != 'Disbursement' THEN 1 ELSE 0 END) AS 'Pipeline Cases', (SELECT COUNT(*) FROM loanspipeline lp WHERE lp.branch_name = main.branch_name AND lp.loan_officer = main.loan_officer) AS 'Total', `average_target` AS 'Average Target', ((SELECT COUNT(*) FROM loanspipeline lp WHERE lp.branch_name = main.branch_name AND lp.loan_officer = main.loan_officer) - `average_target`) AS 'Variance' FROM loanspipeline main GROUP BY `branch_name`, `loan_officer` WITH ROLLUP")
    List<Object []> getLoanOfficerProductivity();
}
