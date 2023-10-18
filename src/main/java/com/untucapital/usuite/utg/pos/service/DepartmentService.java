package com.untucapital.usuite.utg.pos.service;

import com.untucapital.usuite.utg.pos.model.Department;
import com.untucapital.usuite.utg.pos.reposiotory.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public Department saveDepartment(Department department){
        return departmentRepository.save(department);
    }
    //get department by id
    public Department getDepartmentById(Integer departmentId){
        return departmentRepository.findById(departmentId).orElse(null);
    }
    //get all departments
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }
    //update department
    public Department updateDepartment(Department department){
        Department existingDepartment = departmentRepository.findById(department.getId()).orElse(null);

        assert existingDepartment != null;
        existingDepartment.setName(department.getName());
        return departmentRepository.save(existingDepartment);
    }
    //delete department
    public String deleteDepartment(Integer departmentId){
        Department exist = departmentRepository.findById(departmentId).orElse(null);

        if(exist == null){
            return "Department does not exist";
        }

        departmentRepository.delete(exist);
        return "Department deleted successfully";
    }
}
