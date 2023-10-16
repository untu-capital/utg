package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.model.Branches;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.Authorisation;
import com.untucapital.usuite.utg.repository.cms.AuthorisationRepository;
import com.untucapital.usuite.utg.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class AuthorisationService extends AbstractService<Authorisation> {

    private static final Logger log = LoggerFactory.getLogger(AuthorisationService.class);


    private  final AuthorisationRepository authorisationRepository;

    public AuthorisationService(AuthorisationRepository authorisationRepository) {
        this.authorisationRepository = authorisationRepository;
    }

    public void  saveAuthorisation(Authorisation authorisation){
        authorisationRepository.save(authorisation);
    }

    @Override
    protected JpaRepository<Authorisation, String> getRepository(){
        return authorisationRepository;
    }

    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }

    public void deleteAuthorisation(String id) {
        authorisationRepository.deleteById(id);
    }

    public Authorisation getAuthorisationById(String id) {
        return authorisationRepository.findAuthorisationById(id);
    }

    public List<Authorisation> getAuthorisationByBranchId(String branchId){
        return authorisationRepository.findAuthorisationByBranchId(branchId);
    }

}




