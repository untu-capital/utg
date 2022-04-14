package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.CurrentAsset;
import com.untucapital.usuite.utg.repository.CurrentAssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CurrentAssetsService {
    @Autowired
    CurrentAssetsRepository currentAssetsRepository;

    public void saveCurrentAssets(CurrentAsset currentAsset){
        currentAssetsRepository.save(currentAsset);
    }

    public List<CurrentAsset> getCurrentAssets(String loanId){
        return currentAssetsRepository.findCurrentAssetByLoanId(loanId);
    }

    public void deleteCurrentAsset(String id){
        currentAssetsRepository.deleteById(id);
    }
}
