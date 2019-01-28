package com.jazasoft.seltest.service;

import com.jazasoft.seltest.model.Product;
import com.jazasoft.seltest.repository.ProductRepository;
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
public class ProductService {


    ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findOne(Long id){
        return productRepository.findOne(id);
    }

    public Page<Product> findAll(Specification<Product> spec, Pageable pageable) {
        return productRepository.findAll(spec, pageable);
    }


    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional (value="tenantTransactionManager")
    public Product update(Product product){
        Product product1=productRepository.findOne(product.getId());
        product1.setName(product.getName());
        product1.setCategory(product.getCategory());
        product1.setDescription(product.getDescription());
        return product1;
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
    public long count() {
        return productRepository.count();
    }
}
