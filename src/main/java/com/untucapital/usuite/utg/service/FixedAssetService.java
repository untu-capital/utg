package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.CurrentAsset;
import com.untucapital.usuite.utg.model.FixedAsset;
//import com.untucapital.usuite.utg.repository.FixedAssetsRepository;
import com.untucapital.usuite.utg.repository.FixedAssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@javax.transaction.Transactional
public class FixedAssetService {
    @Autowired
    FixedAssetsRepository fixedAssetsRepository;

    @Transactional(value = "transactionManager")
    public void saveFixedAssets(FixedAsset fixedAsset){
        fixedAssetsRepository.save(fixedAsset);
    }

    @Transactional(value = "transactionManager")
    public List<FixedAsset> getFixedAssets(String loanId){
        return fixedAssetsRepository.findFixedAssetByLoanId(loanId);
    }

    @Transactional(value = "transactionManager")
    public void deleteFixedAsset(String id){
        fixedAssetsRepository.deleteById(id);
    }
}
