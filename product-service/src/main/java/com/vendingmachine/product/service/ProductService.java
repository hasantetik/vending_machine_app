package com.vendingmachine.product.service;

import com.vendingmachine.product.dto.User;
import com.vendingmachine.product.entity.Product;
import com.vendingmachine.product.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestTemplate restTemplate;

    public String saveProduct(Product product) {
       User user = getUser(product);
       String role = user.getRoleFields();
       if(role.equals("seller")){
           productRepository.save(product);
           return "1";
       }else
           return null;
    }

    public User getUser(Product product){
        Long id = product.getSellerId();
        User user =restTemplate.getForObject("http://USER-SERVICE/users/findUserById/"+id,User.class);
        return user;
    }

    public String updateProduct(Product product, Long userId) {
        Product existingProduct = getProductById(product.getProductId());
        if(userId==existingProduct.getSellerId()){
            existingProduct.setProductName(product.getProductName());
            existingProduct.setCost(product.getCost());
            existingProduct.setAmountAvailable(product.getAmountAvailable());
            existingProduct.setSellerId(product.getSellerId());
            productRepository.save(existingProduct);
            return "1";
        }else
            return null;
    }

    public String deleteProduct(Long userId,Long id) {
        Product product = getProductById(id);
        if(userId==product.getSellerId()){
            productRepository.deleteById(id);
            return "product removed ! " + id;
        }
        else
          return  "unauthorized !!";
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public String sellProduct(Product product) {
        Product existingProduct = getProductById(product.getProductId());
        existingProduct.setAmountAvailable(product.getAmountAvailable());
        productRepository.save(existingProduct);
        return "1";
    }
}
