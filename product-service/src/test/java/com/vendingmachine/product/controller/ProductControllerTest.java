package com.vendingmachine.product.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendingmachine.product.entity.Product;
import com.vendingmachine.product.repository.ProductRepository;
import com.vendingmachine.product.service.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@DisplayName("Product Test")
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    private ProductService productService;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void saveProduct() throws Exception {
        Product newProduct = new Product(5,2,"su",1L);

        Mockito.when(productService.saveProduct(newProduct)).thenReturn("1");

        String url = "/products/insert";
         mockMvc.perform(MockMvcRequestBuilders.post(url).contentType("application/json")
                        .content(objectMapper.writeValueAsString(newProduct))
                ).andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void updateProduct() throws Exception {
        Long userId=1L;
        Product existProduct = new Product(1L,5,2,"su",1L);

        Mockito.when(productService.updateProduct(existProduct,userId)).thenReturn("1");

        String url = "/products/update";
        mockMvc.perform(MockMvcRequestBuilders.put(url).param("userId", String.valueOf(userId)).contentType("application/json")
                        .content(objectMapper.writeValueAsString(existProduct))
                ).andExpect(status().isOk())
                .andExpect(content().string("1"))
                .andDo(print());
    }

    @Test
    void getAllProduct() throws Exception {
        List<Product> productList = new ArrayList<>();
       productList.add(new Product(1L,5,2,"su",1L));
        productList.add(new Product(2L,4,3,"kek",1L));

        Mockito.when(productService.getAll()).thenReturn(productList);
        String url = "/products/getAll";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)).andExpect(status().isOk()).andReturn();
        String actualJsonResponse= mvcResult.getResponse().getContentAsString();
        System.out.println(actualJsonResponse);
        String expectedJsonResponse = objectMapper.writeValueAsString(productList);
        assertThat(actualJsonResponse).isEqualToIgnoringWhitespace(expectedJsonResponse);
    }

    @Test
    void findProductById() throws Exception {
        Mockito.when(productService.getProductById(5L)).thenReturn(new Product(1L,5,2,"su",1L));
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders.get("/products/findProductById/5").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();
        String ogr = result.getResponse().getContentAsString();
        Product result_product = new ObjectMapper().readValue(ogr, Product.class);
        Assertions.assertEquals(result_product,new Product(1L,5,2,"su",1L));
    }

    @Test
    void sellProduct() throws Exception {
        Product product = new Product(1L,5,2,"su",1L);

        Mockito.when(productService.sellProduct(product)).thenReturn("1");

        String url = "/products/sellProduct";
        mockMvc.perform(MockMvcRequestBuilders.post(url).contentType("application/json")
                        .content(objectMapper.writeValueAsString(product))
                ).andExpect(status().isOk())
                .andExpect(content().string("1"))
                .andDo(print());
    }
}