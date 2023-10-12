package com.untucapital.usuite.utg.repository;


import com.untucapital.usuite.utg.model.clientsDatasets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDatasetsRepository extends JpaRepository<clientsDatasets, String > {


     clientsDatasets findClientsDatasetsById(Long id);
     List<clientsDatasets> findByBranch(String branch);
}
