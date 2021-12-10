package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.ClientLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<ClientLoan, String> {
}
