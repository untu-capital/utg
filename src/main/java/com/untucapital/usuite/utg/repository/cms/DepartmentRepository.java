package com.untucapital.usuite.utg.repository.cms;

import com.untucapital.usuite.utg.model.Department;
import com.untucapital.usuite.utg.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author tjchidanika
 * @created 7/9/2023
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
