package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.SignatureUpload;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignatureUploadRepository extends JpaRepository<SignatureUpload, String> {

    SignatureUpload findSignatureUploadByUserId (String userId);

}
