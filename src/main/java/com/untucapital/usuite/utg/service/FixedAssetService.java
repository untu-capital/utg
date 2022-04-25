package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.CurrentAsset;
import com.untucapital.usuite.utg.model.FixedAsset;
//import com.untucapital.usuite.utg.repository.FixedAssetsRepository;
import com.untucapital.usuite.utg.repository.FixedAssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FixedAssetService {
    @Autowired
    FixedAssetsRepository fixedAssetsRepository;

    public void saveFixedAssets(FixedAsset fixedAsset){
        fixedAssetsRepository.save(fixedAsset);
    }

    public List<FixedAsset> getFixedAssets(String loanId){
        return fixedAssetsRepository.findFixedAssetByLoanId(loanId);
    }

    public void deleteFixedAsset(String id){
        fixedAssetsRepository.deleteById(id);
    }
}
