//package com.untucapital.usuite.utg.service;
//
//
//import com.untucapital.usuite.utg.pos.model.Department;
//import com.untucapital.usuite.utg.pos.repository.DepartmentRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
///**
// * @author tjchidanika
// * @created 7/9/2023
// */
//
//@Service
//@RequiredArgsConstructor
//public class DepartmentService {
//    private final DepartmentRepository departmentRepository;
//
//    //save department
//    @Transactional(value = "transactionManager")
//    public Department saveDepartment(Department department){
//        return departmentRepository.save(department);
//    }
//    //get department by id
//    @Transactional(value = "transactionManager")
//    public Department getDepartmentById(Integer departmentId){
//        return departmentRepository.findById(departmentId).orElse(null);
//    }
//    //get all departments
//    @Transactional(value = "transactionManager")
//    public List<Department> getAllDepartments(){
//        return departmentRepository.findAll();
//    }
//    //update department
//    @Transactional(value = "transactionManager")
//    public Department updateDepartment(Department department){
//        Department existingDepartment = departmentRepository.findById(department.getId()).orElse(null);
//
//        assert existingDepartment != null;
//        existingDepartment.setName(department.getName());
//        return departmentRepository.save(existingDepartment);
//    }
//    //delete department
//    @Transactional(value = "transactionManager")
//    public String deleteDepartment(Integer departmentId){
//        Department exist = departmentRepository.findById(departmentId).orElse(null);
//
//        if(exist == null){
//            return "Department does not exist";
//        }
//
//        departmentRepository.delete(exist);
//        return "Department deleted successfully";
//    }
//}
