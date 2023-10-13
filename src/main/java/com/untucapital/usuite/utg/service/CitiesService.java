package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Cities;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.CitiesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class CitiesService extends AbstractService<Cities> {

    private static final Logger log = LoggerFactory.getLogger(CitiesService.class);

    private final CitiesRepository citiesRepository;

    public CitiesService(CitiesRepository citiesRepository) {
        this.citiesRepository = citiesRepository;
    }

    public void saveCities(Cities cities) {
        citiesRepository.save(cities);
    }

    @Override
    protected JpaRepository<Cities, String> getRepository() {
        return citiesRepository;
    }

    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }
}
