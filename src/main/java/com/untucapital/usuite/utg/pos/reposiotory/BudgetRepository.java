package com.untucapital.usuite.utg.pos.reposiotory;

import com.untucapital.usuite.utg.pos.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tjchidanika
 * @created 5/9/2023
 */

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {
}
