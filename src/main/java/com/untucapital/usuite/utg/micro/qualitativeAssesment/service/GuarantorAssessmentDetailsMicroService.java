package com.untucapital.usuite.utg.micro.qualitativeAssesment.service;

import com.untucapital.usuite.utg.micro.qualitativeAssesment.model.GuarantorAssessmentDetailsMicro;
import com.untucapital.usuite.utg.micro.qualitativeAssesment.repository.GuarantorAssessmentDetailsMicroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GuarantorAssessmentDetailsMicroService {
    @Autowired
    private final GuarantorAssessmentDetailsMicroRepository guarantorAssessmentDetailsMicroRepository;

    public GuarantorAssessmentDetailsMicroService(GuarantorAssessmentDetailsMicroRepository guarantorAssessmentDetailsMicroRepository) {
        this.guarantorAssessmentDetailsMicroRepository = guarantorAssessmentDetailsMicroRepository;
    }

    public void save(GuarantorAssessmentDetailsMicro guarantorAssessmentDetailsMicro) {
        guarantorAssessmentDetailsMicroRepository.save(guarantorAssessmentDetailsMicro);
    }

    public void deleteById(String id) {
        guarantorAssessmentDetailsMicroRepository.deleteById(id);
    }

    public List<GuarantorAssessmentDetailsMicro> findAllByLoanId(String id) {
        return guarantorAssessmentDetailsMicroRepository.findAllByLoanId(id);
    }
}
