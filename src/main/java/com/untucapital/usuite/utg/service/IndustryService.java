package com.untucapital.usuite.utg.service;

import com.untucapital.usuite.utg.model.Industry;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.IndustryRepository;
import com.untucapital.usuite.utg.untuSite.response.GlobalImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class IndustryService extends AbstractService<Industry> {
    @Autowired
    private final GlobalImageService globalImageService;

    private static final Logger log = LoggerFactory.getLogger(IndustryService.class);

    private final IndustryRepository industryRepository;

    public IndustryService(GlobalImageService globalImageService, IndustryRepository industryRepository) {
        this.globalImageService = globalImageService;
        this.industryRepository = industryRepository;
    }

    @Override
    protected JpaRepository<Industry, String> getRepository() {
        return industryRepository;
    }

    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }

    public String addIndustry(MultipartFile file, String name, int code) {
        Industry industry = new Industry();
        String imageUrl= globalImageService.saveFile(file);

        industry.setName(name);
        industry.setCode(code);
        industry.setImageUrl(imageUrl);

        industryRepository.save(industry);
        return imageUrl;
    }
}
