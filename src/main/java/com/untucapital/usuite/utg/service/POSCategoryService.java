package com.untucapital.usuite.utg.service;


import com.untucapital.usuite.utg.model.POSCategory;
import com.untucapital.usuite.utg.repository.POSCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tjchidanika
 * @created 11/9/2023
 */

@Service
@RequiredArgsConstructor
public class POSCategoryService {
    private final POSCategoryRepository posCategoryRepository;
    //save category
    public POSCategory saveCategory(POSCategory posCategory){
        return posCategoryRepository.save(posCategory);
    }
    //get category by id
    public POSCategory getCategoryById(Integer categoryId){
        return posCategoryRepository.findById(categoryId).orElse(null);
    }
    //get all categories
    public List<POSCategory> getAllCategories(){
        return posCategoryRepository.findAll();
    }
    //update category
    public POSCategory updateCategory(POSCategory posCategory){
        POSCategory existingCategory = posCategoryRepository.findById(posCategory.getId()).orElse(null);

        assert existingCategory != null;
        existingCategory.setName(posCategory.getName());
        return posCategoryRepository.save(existingCategory);
    }
    //delete category
    public String deleteCategory(Integer categoryId){
        POSCategory exist = posCategoryRepository.findById(categoryId).orElse(null);

        if(exist == null){
            return "Category does not exist";
        }

        posCategoryRepository.delete(exist);
        return "Category deleted successfully";
    }
}
