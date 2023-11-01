package com.untucapital.usuite.utg.service.cms;

import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.model.cms.Authorisation;
import com.untucapital.usuite.utg.repository.cms.AuthorisationRepository;
import com.untucapital.usuite.utg.service.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@javax.transaction.Transactional
public class AuthorisationService extends AbstractService<Authorisation> {

    private static final Logger log = LoggerFactory.getLogger(AuthorisationService.class);


    private  final AuthorisationRepository authorisationRepository;


    public AuthorisationService(AuthorisationRepository authorisationRepository) {
        this.authorisationRepository = authorisationRepository;
    }

    @Transactional(value = "transactionManager")
    public void  saveAuthorisation(Authorisation authorisation){
        authorisationRepository.save(authorisation);
    }

    @Override
    @Transactional(value = "transactionManager")
    public JpaRepository<Authorisation, String> getRepository(){
        return authorisationRepository;
    }

    @Override
    @Transactional(value = "transactionManager")
    public List<User> getUserByRole(String name) {
        return null;
    }

    @Transactional(value = "transactionManager")
    public void deleteAuthorisation(String id) {
        authorisationRepository.deleteById(id);
    }

    @Transactional(value = "transactionManager")
    public Authorisation getAuthorisationById(String id) {
        return authorisationRepository.findAuthorisationById(id);
    }

    @Transactional(value = "transactionManager")
    public List<Authorisation> getAuthorisationByBranchId(String branchId){
        return authorisationRepository.findAuthorisationByBranchId(branchId);
    }

    @Transactional(value = "transactionManager")
    public List<Authorisation> getAuthorisationByBranchIdAndAuthLevel(String branchId, String authLevel){
        return authorisationRepository.findAuthorisationByBranchIdAndAuthLevel(branchId, authLevel);
    }

}




