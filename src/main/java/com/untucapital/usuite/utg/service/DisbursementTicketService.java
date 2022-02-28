package com.untucapital.usuite.utg.service;
import com.untucapital.usuite.utg.model.DisbursementTicket;
import com.untucapital.usuite.utg.model.User;
import com.untucapital.usuite.utg.repository.DisbursementTicketRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Service
public class DisbursementTicketService extends AbstractService<DisbursementTicket> {

    private static final Logger log = LoggerFactory.getLogger(DisbursementTicketService.class);

    private final DisbursementTicketRepository disbursementTicketRepository;

    public DisbursementTicketService(DisbursementTicketRepository disbursementTicketRepository) {
        this.disbursementTicketRepository = disbursementTicketRepository;
    }
    public void saveDisbursementTicket(DisbursementTicket disbursementTicket) {
        disbursementTicketRepository.save(disbursementTicket);
    }
    @Override
    public List<User> getUserByRole(String name) {
        return null;
    }

    @Override
    protected JpaRepository<DisbursementTicket, String> getRepository() {
        return disbursementTicketRepository;
    }

        public void deleteDisbursementTicket(String id) {
            disbursementTicketRepository.deleteById(id);
    }


    }




