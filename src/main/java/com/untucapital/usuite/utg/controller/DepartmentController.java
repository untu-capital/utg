//package com.untucapital.usuite.utg.controller;
//
//
//import com.untucapital.usuite.utg.pos.model.Department;
//import com.untucapital.usuite.utg.service.DepartmentService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
///**
// * @author tjchidanika
// * @created 7/9/2023
// */
//@RestController
//@RequestMapping("/pos/department")
//@RequiredArgsConstructor
//public class DepartmentController {
//    private final DepartmentService departmentService;
//
//    //save department
//    @PostMapping("/save")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Department saveDepartment(@RequestBody Department department){
//        return departmentService.saveDepartment(department);
//    }
//    //get department by id
//    @GetMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Department getDepartmentById(@PathVariable Integer id){
//        return departmentService.getDepartmentById(id);
//    }
//    //get all departments
//    @GetMapping("/all")
//    @ResponseStatus(HttpStatus.OK)
//    public List<Department> getAllDepartments(){
//        return departmentService.getAllDepartments();
//    }
//    //update department
//    @PutMapping("/update")
//    @ResponseStatus(HttpStatus.OK)
//    public Department updateDepartment(@RequestBody Department department){
//        return departmentService.updateDepartment(department);
//    }
//    //delete department
//    @DeleteMapping("/delete/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public String deleteDepartment(@PathVariable Integer id){
//        return departmentService.deleteDepartment(id);
//    }
//}
