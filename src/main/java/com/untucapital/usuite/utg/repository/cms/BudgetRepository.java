package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Repository
public interface BudgetRepository extends JpaRepository<Stock.Budget, Integer> {
}
