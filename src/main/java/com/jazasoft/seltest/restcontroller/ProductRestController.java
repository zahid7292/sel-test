package com.jazasoft.seltest.restcontroller;

import com.jazasoft.mtdb.specification.CustomRsqlVisitor;
import com.jazasoft.seltest.ApiUrls;
import com.jazasoft.seltest.model.Product;
import com.jazasoft.seltest.service.ProductService;
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
@RequestMapping(ApiUrls.ROOT_URL_PRODUCTS)
public class ProductRestController {

    private ProductService productService;

    @Autowired
    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<?> getCategories(@RequestParam(value = "search",defaultValue = "") String search, Pageable pageable){
        Page<Product> pages;
        if (search.trim().isEmpty()) {
            pages = productService.findAll(pageable);
        } else {
            Node rootNode = new RSQLParser().parse(search);
            Specification<Product> spec = rootNode.accept(new CustomRsqlVisitor<Product>());
            pages = productService.findAll(spec, pageable);
        }
        return ResponseEntity.ok(pages);
    }


    @GetMapping(ApiUrls.URL_PRODUCTS_PRODUCT)
    public ResponseEntity<?> getProduct(@PathVariable("productId") Long productId){
        Product product = productService.findOne(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Product product){
        product = productService.save(product);
        return ResponseEntity.ok(product);
    }
    @DeleteMapping(ApiUrls.URL_PRODUCTS_PRODUCT)
    public ResponseEntity<?>deleteProduct(@PathVariable ("productId") Long productId){
        productService.delete(productId);
        return ResponseEntity.ok(productId);
    }
    @PutMapping(ApiUrls.URL_PRODUCTS_PRODUCT)
    public ResponseEntity<?> updateProduct(@PathVariable("productId") Long productId,@Valid @RequestBody Product product){
        product.setId(productId);
        Product product1=productService.update(product);
        return ResponseEntity.ok(product1);
    }
}
