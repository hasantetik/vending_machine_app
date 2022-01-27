package com.vendingmachine.product.controller;

import com.vendingmachine.product.entity.Product;
import com.vendingmachine.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/insert")
    public String saveProduct(@RequestBody Product product){
        log.info("inside saveProduct method of ProductController");
        return productService.saveProduct(product);
    }
    @PutMapping("/update")
    public String updateProduct(@RequestBody Product product,@RequestParam Long userId){
        log.info("inside updateProduct method of ProductController");
        return productService.updateProduct(product,userId);
    }
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id,@RequestParam Long userId){
        log.info("inside deleteProduct method of ProductController");
        return  productService.deleteProduct(userId,id);
    }
    @GetMapping("/getAll")
    public List<Product> getAllProduct(){
        log.info("inside getAllProduct method of ProductController");
        return productService.getAll();
    }
    @GetMapping("/findProductById/{id}")
    public Product findProductById(@PathVariable Long id){
        log.info("inside findProductById method of ProductController");
        return productService.getProductById(id);
    }
    @PostMapping("/sellProduct")
    public String sellProduct(@RequestBody Product product){
        log.info("inside sellProduct method of ProductController");
        return productService.sellProduct(product);
    }
}
