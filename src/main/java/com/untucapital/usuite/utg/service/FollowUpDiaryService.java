package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.FollowUpDiary;
import com.untucapital.usuite.utg.repository.FollowUpDiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowUpDiaryService {

    private final FollowUpDiaryRepository followUpDiaryRepository;

    @Autowired
    public FollowUpDiaryService(FollowUpDiaryRepository followUpDiaryRepository) {
        this.followUpDiaryRepository = followUpDiaryRepository;
    }

    public FollowUpDiary createFollowUpDiary(FollowUpDiary followUpDiary) {
        return followUpDiaryRepository.save(followUpDiary);
    }

    public FollowUpDiary updateFollowUpDiary(String id, FollowUpDiary followUpDiary) {
        Optional<FollowUpDiary> optionalFollowUpDiary = followUpDiaryRepository.findById(id);
        if (optionalFollowUpDiary.isPresent()) {
            FollowUpDiary existingFollowUpDiary = optionalFollowUpDiary.get();
            existingFollowUpDiary.setClientName(followUpDiary.getClientName());
            existingFollowUpDiary.setClientContactNumber(followUpDiary.getClientContactNumber());
            existingFollowUpDiary.setClientContactEmail(followUpDiary.getClientContactEmail());
            existingFollowUpDiary.setClientBusinessAddress(followUpDiary.getClientBusinessAddress());
            existingFollowUpDiary.setFollowUpComments(followUpDiary.getFollowUpComments());
            existingFollowUpDiary.setContacted(followUpDiary.getContacted());
            existingFollowUpDiary.setFollowUpStatus(followUpDiary.getFollowUpStatus());
            existingFollowUpDiary.setLoanOfficerName(followUpDiary.getLoanOfficerName());
            return followUpDiaryRepository.save(existingFollowUpDiary);
        } else {
            return null;
        }
    }

    public FollowUpDiary getFollowUpDiaryById(String id) {
        Optional<FollowUpDiary> optionalFollowUpDiary = followUpDiaryRepository.findById(id);
        return optionalFollowUpDiary.orElse(null);
    }
    public List<FollowUpDiary> getFollowUpDiaryByClientid(String clientID) {
        List<FollowUpDiary> optionalFollowUpDiary = followUpDiaryRepository.findFollowUpDiaryByClientID(clientID);
        return optionalFollowUpDiary;
    }

    public List<FollowUpDiary> getAllFollowUpDiaries() {
        return followUpDiaryRepository.findAll();
    }

    public void deleteFollowUpDiary(String id) {
        followUpDiaryRepository.deleteById(id);
    }
}
