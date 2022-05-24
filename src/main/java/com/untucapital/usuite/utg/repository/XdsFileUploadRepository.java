package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.XdsFileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface XdsFileUploadRepository extends JpaRepository<XdsFileUpload, String> {

    XdsFileUpload findXdsFileUploadByLoanId(String loanId);
}
