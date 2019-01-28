package com.jazasoft.seltest.restcontroller;

import com.jazasoft.mtdb.specification.CustomRsqlVisitor;
import com.jazasoft.seltest.ApiUrls;
import com.jazasoft.seltest.model.Category;
import com.jazasoft.seltest.repository.CategoryRepository;
import com.jazasoft.seltest.service.CategoryService;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by mdzahidraza on 28/06/17.
 */
@RestController
@RequestMapping(ApiUrls.ROOT_URL_CATEGORIES)
public class CategoryRestController {

    private CategoryService categoryService;
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<?> getCategories(@RequestParam(value = "search",defaultValue = "") String search, Pageable pageable){
        Page<Category> pages;
        if (search.trim().isEmpty()) {
            pages = categoryService.findAll(pageable);
        } else {
            Node rootNode = new RSQLParser().parse(search);
            Specification<Category> spec = rootNode.accept(new CustomRsqlVisitor<Category>());
            pages = categoryService.findAll(spec, pageable);
        }
        return ResponseEntity.ok(pages);
    }


    @GetMapping(ApiUrls.URL_CATEGORIES_CATEGORY)
    public ResponseEntity<?> getCategory(@PathVariable("categoryId") Long categoryId){
        Category category = categoryService.findOne(categoryId);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PostMapping
    public ResponseEntity<?> saveCategory(@RequestBody Category category){
        category = categoryService.save(category);
        return ResponseEntity.ok(category);
    }
    @PutMapping(ApiUrls.URL_CATEGORIES_CATEGORY)
    public ResponseEntity<?>updateCategory(@PathVariable("categoryId") Long categoryId,@Valid @RequestBody Category category){

        category.setId(categoryId);
        Category category1=categoryService.update(category);
        return ResponseEntity.ok(category1);
    }
    @DeleteMapping(ApiUrls.URL_CATEGORIES_CATEGORY)
    public ResponseEntity<?> deleteCategory(@PathVariable("categoryId") Long categoryId ){
//        Category cat=categoryRepository.findOne(categoryId);
//        if(cat==null){
//            return ResponseEntity.notFound().build();
//        }
        categoryService.delete(categoryId);
        return ResponseEntity.ok(categoryId);
    }
}
