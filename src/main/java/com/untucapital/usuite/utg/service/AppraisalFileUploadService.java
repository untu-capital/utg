package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.dto.request.AppraisalFileUploadRequestDTO;
import com.untucapital.usuite.utg.dto.response.AppraisalFileUploadResponseDTO;
import com.untucapital.usuite.utg.model.AppraisalFileUpload;
import com.untucapital.usuite.utg.repository.AppraisalFileUploadRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppraisalFileUploadService {
    @Autowired
    private final AppraisalFileUploadRepository appraisalFileUploadRepository;

    public AppraisalFileUploadService(AppraisalFileUploadRepository appraisalFileUploadRepository) {
        this.appraisalFileUploadRepository = appraisalFileUploadRepository;
    }

    @Transactional(value = "transactionManager")
    public void save(AppraisalFileUploadRequestDTO requestDTO){

        AppraisalFileUpload appraisalFileUpload = new AppraisalFileUpload();
        BeanUtils.copyProperties(requestDTO, appraisalFileUpload);
        appraisalFileUploadRepository.save(appraisalFileUpload);
    }

    @Transactional(value = "transactionManager")
    public void delete(String id){
        appraisalFileUploadRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public List<AppraisalFileUploadResponseDTO> findByLoanId(String loanId) {

        List<AppraisalFileUploadResponseDTO> response = new ArrayList<AppraisalFileUploadResponseDTO>();
        List<AppraisalFileUpload> appraisalFileUploadList =appraisalFileUploadRepository.findByLoanId(loanId);

        for(AppraisalFileUpload appraisalFileUpload : appraisalFileUploadList){
            AppraisalFileUploadResponseDTO responseDTO = new AppraisalFileUploadResponseDTO();
            BeanUtils.copyProperties(appraisalFileUpload, responseDTO);

            response.add(responseDTO);
        }
        return  response;
    }

    @Transactional(value = "transactionManager")
    public void updateAppraisalFile( String loanId, AppraisalFileUploadRequestDTO request){

        AppraisalFileUploadRequestDTO appraisalFileUploadRequestDTO = new AppraisalFileUploadRequestDTO();
        AppraisalFileUpload updateAppraisalFile = appraisalFileUploadRepository.findAppraisalFileUploadByLoanId(loanId);
        updateAppraisalFile.setFileName(request.getFileName());
        BeanUtils.copyProperties(updateAppraisalFile, appraisalFileUploadRequestDTO);
        save(appraisalFileUploadRequestDTO);
    }
}
