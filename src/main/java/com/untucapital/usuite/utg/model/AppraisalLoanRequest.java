package com.untucapital.usuite.utg.model;

import com.sun.istack.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "appraisal_loan_requests")
public class AppraisalLoanRequest extends AbstractEntity {

    private  String userId;

    private  String loanOfficerFirstName;

    private  String loanOfficerLastName;

    private String loanId;

    private String investmentPlan;

    private String quotation;

    private String whyQuotation;

    private String loanToSupplier;

    private String whyloanToSupplier;

    private String percentageFixedAssets;

    private String percentageWorkingCapital;

    private String source;

    private String sourceAmount;

    private String percentageShare;

    private String verified;

    private String comments;

    private String loanAmountClient;

    private String maturityClient;

    private String interestRateCLient;

    private String installmentClient;

    private String loanAmountLoanOfficer;

    private String maturityLoanOfficer;

    private String interestRateLoanOfficer;

    private String installmentLoanOfficer;

    private String commentsTermAndCondition;

    public String getInvestmentPlan() {
        return investmentPlan;
    }

    public void setInvestmentPlan(String investmentPlan) {
        this.investmentPlan = investmentPlan;
    }

    public String getQuotation() {
        return quotation;
    }

    public void setQuotation(String quotation) {
        this.quotation = quotation;
    }

    public String getWhyQuotation() {
        return whyQuotation;
    }

    public void setWhyQuotation(String whyQuotation) {
        this.whyQuotation = whyQuotation;
    }

    public String getLoanToSupplier() {
        return loanToSupplier;
    }

    public void setLoanToSupplier(String loanToSupplier) {
        this.loanToSupplier = loanToSupplier;
    }

    public String getWhyloanToSupplier() {
        return whyloanToSupplier;
    }

    public void setWhyloanToSupplier(String whyloanToSupplier) {
        this.whyloanToSupplier = whyloanToSupplier;
    }

    public String getPercentageFixedAssets() {
        return percentageFixedAssets;
    }

    public void setPercentageFixedAssets(String percentageFixedAssets) {
        this.percentageFixedAssets = percentageFixedAssets;
    }

    public String getPercentageWorkingCapital() {
        return percentageWorkingCapital;
    }

    public void setPercentageWorkingCapital(String percentageWorkingCapital) {
        this.percentageWorkingCapital = percentageWorkingCapital;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSourceAmount() {
        return sourceAmount;
    }

    public void setSourceAmount(String sourceAmount) {
        this.sourceAmount = sourceAmount;
    }

    public String getPercentageShare() {
        return percentageShare;
    }

    public void setPercentageShare(String percentageShare) {
        this.percentageShare = percentageShare;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getLoanAmountClient() {
        return loanAmountClient;
    }

    public void setLoanAmountClient(String loanAmountClient) {
        this.loanAmountClient = loanAmountClient;
    }

    public String getMaturityClient() {
        return maturityClient;
    }

    public void setMaturityClient(String maturityClient) {
        this.maturityClient = maturityClient;
    }

    public String getInterestRateCLient() {
        return interestRateCLient;
    }

    public void setInterestRateCLient(String interestRateCLient) {
        this.interestRateCLient = interestRateCLient;
    }

    public String getInstallmentClient() {
        return installmentClient;
    }

    public void setInstallmentClient(String installmentClient) {
        this.installmentClient = installmentClient;
    }

    public String getLoanAmountLoanOfficer() {
        return loanAmountLoanOfficer;
    }

    public void setLoanAmountLoanOfficer(String loanAmountLoanOfficer) {
        this.loanAmountLoanOfficer = loanAmountLoanOfficer;
    }

    public String getMaturityLoanOfficer() {
        return maturityLoanOfficer;
    }

    public void setMaturityLoanOfficer(String maturityLoanOfficer) {
        this.maturityLoanOfficer = maturityLoanOfficer;
    }

    public String getInterestRateLoanOfficer() {
        return interestRateLoanOfficer;
    }

    public void setInterestRateLoanOfficer(String interestRateLoanOfficer) {
        this.interestRateLoanOfficer = interestRateLoanOfficer;
    }

    public String getInstallmentLoanOfficer() {
        return installmentLoanOfficer;
    }

    public void setInstallmentLoanOfficer(String installmentLoanOfficer) {
        this.installmentLoanOfficer = installmentLoanOfficer;
    }

    public String getCommentsTermAndCondition() {
        return commentsTermAndCondition;
    }

    public void setCommentsTermAndCondition(String commentsTermAndCondition) {
        this.commentsTermAndCondition = commentsTermAndCondition;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoanOfficerFirstName() {
        return loanOfficerFirstName;
    }

    public void setLoanOfficerFirstName(String loanOfficerFirstName) {
        this.loanOfficerFirstName = loanOfficerFirstName;
    }

    public String getLoanOfficerLastName() {
        return loanOfficerLastName;
    }

    public void setLoanOfficerLastName(String loanOfficerLastName) {
        this.loanOfficerLastName = loanOfficerLastName;
    }
}









