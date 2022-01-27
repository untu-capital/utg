package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Industry;
import com.untucapital.usuite.utg.repository.IndustryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service
public class IndustryService extends AbstractService<Industry> {

    private static final Logger log = LoggerFactory.getLogger(IndustryService.class);

    private final IndustryRepository industryRepository;

    public IndustryService(IndustryRepository industryRepository) {
        this.industryRepository = industryRepository;
    }

    @Override
    protected JpaRepository<Industry, String> getRepository() {
        return industryRepository;
    }
}
