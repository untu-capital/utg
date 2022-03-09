package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Business;
import com.untucapital.usuite.utg.model.Cities;
import com.untucapital.usuite.utg.model.Meetings;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.CitiesRepository;
import com.untucapital.usuite.utg.repository.MeetingsRepository;
import com.untucapital.usuite.utg.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class MeetingsService extends AbstractService<Meetings>{

    private static final Logger log = LoggerFactory.getLogger(MeetingsService.class);

    private final MeetingsRepository meetingsRepository;

    public MeetingsService(MeetingsRepository meetingsRepository) {
        this.meetingsRepository = meetingsRepository;
    }
    public void saveMeetings(Meetings meetings) {
        meetingsRepository.save(meetings);
    }


    @Override
    protected JpaRepository<Meetings, String> getRepository() {
        return meetingsRepository;
    }

    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }
}
