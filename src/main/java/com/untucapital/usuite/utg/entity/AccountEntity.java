package com.untucapital.usuite.utg.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AccountLink")
    private int accountLink;

    @Column(name = "Master_Sub_Account")
    private String masterSubAccount;

    @Column(name = "AccountLevel")
    private int accountLevel;

    @Column(name = "Account")
    private String account;

    @Column(name = "iAccountType")
    private int iAccountType;

    @Column(name = "SubAccOfLink")
    private int subAccOfLink;

    @Column(name = "Dept")
    private String dept;

    @Column(name = "Brch")
    private String brch;

    @Column(name = "Jr")
    private boolean jr;

    @Column(name = "Description")
    private String description;

    @Column(name = "CaseAcc")
    private String caseAcc;

    @Column(name = "ActiveAccount")
    private boolean activeAccount;

    @Column(name = "dAccountsTimeStamp")
    private String dAccountsTimeStamp;

    @Column(name = "cNextChequeNum")
    private String cNextChequeNum;

    @Column(name = "iGLSegment0ID")
    private int iGLSegment0ID;

    @Column(name = "iGLSegment1ID")
    private int iGLSegment1ID;

    @Column(name = "iGLSegment2ID")
    private int iGLSegment2ID;

    @Column(name = "iGLSegment3ID")
    private int iGLSegment3ID;

    @Column(name = "iGLSegment4ID")
    private int iGLSegment4ID;

    @Column(name = "iGLSegment5ID")
    private int iGLSegment5ID;

    @Column(name = "iGLSegment6ID")
    private int iGLSegment6ID;

    @Column(name = "iGLSegment7ID")
    private int iGLSegment7ID;

    @Column(name = "iGLSegment8ID")
    private int iGLSegment8ID;

    @Column(name = "iGLSegment9ID")
    private int iGLSegment9ID;

    @Column(name = "iReportCategoryID")
    private int iReportCategoryID;

    @Column(name = "fBankStatementBalance")
    private float fBankStatementBalance;

    @Column(name = "cExtDescription")
    private String cExtDescription;

    @Column(name = "iTaxTypeINVID")
    private int iTaxTypeINVID;

    @Column(name = "iTaxTypeCRNID")
    private int iTaxTypeCRNID;

    @Column(name = "iTaxTypeGRVID")
    private int iTaxTypeGRVID;

    @Column(name = "iTaxTypeRTSID")
    private int iTaxTypeRTSID;

    @Column(name = "iAllowICSales")
    private boolean iAllowICSales;

    @Column(name = "iAllowICPurchases")
    private boolean iAllowICPurchases;

    @Column(name = "iMBReportingCategoryID")
    private int iMBReportingCategoryID;

    @Column(name = "iMBCashFlowCategoryID")
    private int iMBCashFlowCategoryID;

    @Column(name = "bMBIsAsset")
    private boolean bMBIsAsset;

    @Column(name = "bMBIsGrant")
    private boolean bMBIsGrant;

    @Column(name = "iMBAssetClassificationID")
    private int iMBAssetClassificationID;

    @Column(name = "iMBAssetCategoryID")
    private int iMBAssetCategoryID;

    @Column(name = "iMBAssetTypeID")
    private int iMBAssetTypeID;

    @Column(name = "iMBGrantLevel1TypeID")
    private int iMBGrantLevel1TypeID;

    @Column(name = "iMBGrantLevel2TypeID")
    private int iMBGrantLevel2TypeID;

    @Column(name = "iMBGrantLevel3TypeID")
    private int iMBGrantLevel3TypeID;

    @Column(name = "bIsBranchLoanAccount")
    private boolean bIsBranchLoanAccount;

    @Column(name = "iForeignBankCurrencyID")
    private int iForeignBankCurrencyID;

    @Column(name = "iForeignBankPEXAccID")
    private int iForeignBankPEXAccID;

    @Column(name = "iForeignBankLEXAccID")
    private int iForeignBankLEXAccID;

    @Column(name = "bRevalueWithSellingRate")
    private boolean bRevalueWithSellingRate;

    @Column(name = "bPaymentsBasedTax")
    private boolean bPaymentsBasedTax;

    @Column(name = "cBankName")
    private String cBankName;

    @Column(name = "cBankAccountName")
    private String cBankAccountName;

    @Column(name = "cBankCode")
    private String cBankCode;

    @Column(name = "cBankAccountNumber")
    private String cBankAccountNumber;

    @Column(name = "cBranchName")
    private String cBranchName;

    @Column(name = "cSEPABranchCode")
    private String cSEPABranchCode;

    @Column(name = "cBankRefNr")
    private String cBankRefNr;

    @Column(name = "iAttributeGroupID")
    private int iAttributeGroupID;

    @Column(name = "xAttribute")
    private String xAttribute;

    @Column(name = "bForeignBankAcc")
    private boolean bForeignBankAcc;

    @Column(name = "Accounts_iBranchID")
    private int accountsIBranchID;

    @Column(name = "Accounts_dCreatedDate")
    private String accountsDCreatedDate;

    @Column(name = "Accounts_dModifiedDate")
    private String accountsDModifiedDate;

    @Column(name = "Accounts_iCreatedBranchID")
    private int accountsICreatedBranchID;

    @Column(name = "Accounts_iModifiedBranchID")
    private int accountsIModifiedBranchID;

    @Column(name = "Accounts_iCreatedAgentID")
    private int accountsICreatedAgentID;

    @Column(name = "Accounts_iModifiedAgentID")
    private int accountsIModifiedAgentID;

    @Column(name = "Accounts_iChangeSetID")
    private int accountsIChangeSetID;

    @Column(name = "Accounts_Checksum")
    private byte[] accountsChecksum;

    @Column(name = "cSBFBankAccountID")
    private String cSBFBankAccountID;

    @Column(name = "ulGLSector1")
    private String ulGLSector1;

    @Column(name = "ulGLSector2")
    private String ulGLSector2;

    @Column(name = "ulGLSector3")
    private String ulGLSector3;

    @Column(name = "ulGLSector4")
    private String ulGLSector4;

    @Column(name = "ulGLSector5")
    private String ulGLSector5;

    @Column(name = "ulGLSector6")
    private String ulGLSector6;

    @Column(name = "ulGLSector7")
    private String ulGLSector7;

    @Column(name = "ulGLSector8")
    private String ulGLSector8;

    @Column(name = "ulGLSector9")
    private String ulGLSector9;

    @Column(name = "ulGLSector10")
    private String ulGLSector10;

    @Column(name = "ulGLCostCentre")
    private String ulGLCostCentre;

}

