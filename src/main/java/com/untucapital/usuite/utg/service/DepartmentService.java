package com.untucapital.usuite.utg.service;


//import com.untucapital.usuite.utg.model.Department;
//import com.untucapital.usuite.utg.repository.DepartmentRepository;
import com.untucapital.usuite.utg.model.Staff;
import com.untucapital.usuite.utg.repository.cms.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author tjchidanika
 * @created 7/9/2023
 */

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    //save department
    @Transactional(value = "transactionManager")
    public Staff.Department saveDepartment(Staff.Department department){
        return departmentRepository.save(department);
    }
    //get department by id
    @Transactional(value = "transactionManager")
    public Staff.Department getDepartmentById(Integer departmentId){
        return departmentRepository.findById(departmentId).orElse(null);
    }
    //get all departments
    @Transactional(value = "transactionManager")
    public List<Staff.Department> getAllDepartments(){
        return departmentRepository.findAll();
    }
    //update department
    @Transactional(value = "transactionManager")
    public Staff.Department updateDepartment(Staff.Department department){
        Staff.Department existingDepartment = departmentRepository.findById(department.getId()).orElse(null);

        assert existingDepartment != null;
        existingDepartment.setName(department.getName());
        return departmentRepository.save(existingDepartment);
    }
    //delete department
    @Transactional(value = "transactionManager")
    public String deleteDepartment(Integer departmentId){
        Staff.Department exist = departmentRepository.findById(departmentId).orElse(null);

        if(exist == null){
            return "Department does not exist";
        }

        departmentRepository.delete(exist);
        return "Department deleted successfully";
    }
}
