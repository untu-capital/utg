package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.DTO.request.XdsFileUploadRequestDTO;
import com.untucapital.usuite.utg.DTO.response.XdsFileUploadResponseDTO;
import com.untucapital.usuite.utg.model.XdsFileUpload;
import com.untucapital.usuite.utg.repository.XdsFileUploadRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;


@Service
@javax.transaction.Transactional
public class XdsFileUploadService {

    @Autowired
    XdsFileUploadRepository xdsFileUploadRepository;

    @Transactional(value = "transactionManager")
    public void save(XdsFileUploadRequestDTO request){

        XdsFileUpload xdsFileUpload = new XdsFileUpload();
        BeanUtils.copyProperties(request, xdsFileUpload);
        xdsFileUploadRepository.save(xdsFileUpload);
    }

    @Transactional(value = "transactionManager")
    public XdsFileUploadResponseDTO getXdsFileUpload(String loanId){

        XdsFileUploadResponseDTO responseDTO = new XdsFileUploadResponseDTO();
        XdsFileUpload xdsFileUpload= xdsFileUploadRepository.findXdsFileUploadByLoanId(loanId);
        BeanUtils.copyProperties(xdsFileUpload,responseDTO);

        return responseDTO;
    }

    @Transactional(value = "transactionManager")
    public void updateXdsFile(String loanId, XdsFileUploadRequestDTO request){

        XdsFileUploadRequestDTO xdsFileUpload = new XdsFileUploadRequestDTO();
        XdsFileUpload updateXdsFile = xdsFileUploadRepository.findXdsFileUploadByLoanId(loanId);
        updateXdsFile.setFileName(request.getFileName());

        BeanUtils.copyProperties(updateXdsFile, xdsFileUpload);
        save(xdsFileUpload);
    }
}
