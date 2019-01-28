package com.jazasoft.seltest.service;

import com.jazasoft.seltest.model.Category;
import com.jazasoft.seltest.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mdzahidraza on 28/06/17.
 */
@Service
@Transactional(value = "tenantTransactionManager")
public class CategoryService {


    CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category findOne(Long id){
        return categoryRepository.findOne(id);
    }

    public Page<Category> findAll(Specification<Category> spec, Pageable pageable){
        return categoryRepository.findAll(spec, pageable);
    }

    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.delete(id);
    }
    public long count() {
        return categoryRepository.count();
    }
}
