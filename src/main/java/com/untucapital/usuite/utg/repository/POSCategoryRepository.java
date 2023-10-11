package com.untucapital.usuite.utg.repository;


import com.untucapital.usuite.utg.model.POSCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tjchidanika
 * @created 11/9/2023
 */
@Repository
public interface POSCategoryRepository extends JpaRepository<POSCategory, Integer> {
}
