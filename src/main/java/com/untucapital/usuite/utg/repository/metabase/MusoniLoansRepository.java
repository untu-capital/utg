package com.untucapital.usuite.utg.repository.metabase;

import com.untucapital.usuite.utg.model.metabase.MetabaseLoanData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface MusoniLoansRepository extends JpaRepository<MetabaseLoanData, Integer> {

}
