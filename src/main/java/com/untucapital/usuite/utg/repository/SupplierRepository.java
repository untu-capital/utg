package com.untucapital.usuite.utg.repository;

import com.untucapital.usuite.utg.model.POSSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Repository
public interface SupplierRepository extends JpaRepository<POSSupplier, Integer> {
}
