package com.vendingmachine.user.service;

import com.vendingmachine.user.dto.Product;
import com.vendingmachine.user.entity.UserEntity;
import com.vendingmachine.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    UserAutService userAutService;

    public String saveUser(UserEntity user) {
        userAutService.save(user);
        return "1";
    }

    public String deleteUser(Long id) {
         userRepository.deleteById(id);
         return "user removed ! " + id;
    }

    public String updateUser(UserEntity user) {
        UserEntity existingUser = userRepository.findById(user.getUserId()).orElse(null);
        existingUser.setUsername(user.getUsername());
        existingUser.setDeposit(user.getDeposit());
        existingUser.setPassword(user.getPassword());
        existingUser.setRoleFields(user.getRoleFields());
        userRepository.save(existingUser);
        return "1";
    }

    public UserEntity getUserById(Long id) {
       return userRepository.findById(id).orElse(null);
    }

    public List<UserEntity> getUsers() {
        return userRepository.findAll();
    }

    public String deposit(Long userId, int deposit) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        String role = user.getRoleFields();
        if((deposit==5 || deposit==10 || deposit==20 || deposit==50 || deposit ==100) && role.equals("buyer")){
            int sumDeposit = user.getDeposit()+deposit;
            user.setDeposit(sumDeposit);
            userRepository.save(user);
            return "userid : " + user.getUserId() + " sum deposit : " + sumDeposit;
        }else
            return "you can only deposit 5,10,20,50,100 and only buyer";
    }

    public void postProduct(Product product){
            URI url = URI.create("http://PRODUCT-SERVICE/products/sellProduct");
            HttpEntity<Product> request = new HttpEntity<>(product);
            restTemplate.postForObject(url, request, String.class);
    }

    public String getProduct(Long productId, int amount, Long userId) {
            UserEntity user = userRepository.findById(userId).orElse(null);
            Product product =restTemplate.getForObject("http://PRODUCT-SERVICE/products/findProductById/"+productId,Product.class);
            int price = amount*(product.getCost());//harcanan para
            int curruntAmount = product.getAmountAvailable()-amount;//kalan ürün
            int currentDeposit = user.getDeposit()-price;//kalan para
        if(user.getRoleFields().equals("buyer") && currentDeposit>=0 && curruntAmount>=0) {
            user.setDeposit(currentDeposit);
            userRepository.save(user);
            product.setAmountAvailable(curruntAmount);
            postProduct(product);
            return "User : " + user.getUsername() + " Total amount : " + price
                    + " Product received : " + product.getProductName()+ "Remaining amount :" + currentDeposit;
        }
        else
            return "incorrect entry";
    }

    public String resetDeposit(Long userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
    if(user.getRoleFields().equals("buyer")){
        String money= payMoney(user.getDeposit());
        user.setDeposit(0);
        userRepository.save(user);
        return money;
    }else
       return "unauthorized !!";
    }
    private String payMoney(int deposit) {
        int hundred = deposit/100;
        int fifties =(deposit-(100*hundred))/50;
        int twenties = (deposit-((hundred*100)+(fifties*50)))/20;
        int apron= (deposit-((hundred*100)+(fifties*50)+(twenties*20)))/10;
        int five = (deposit-((hundred*100)+(fifties*50)+(twenties*20)+(apron*10)))/5;
        int unpaidAmount = (deposit-((hundred*100)+(fifties*50)+(twenties*20)+(apron*10)+(five*5)));
        return "paind amount : "+deposit + ", 100 x "+hundred+", 50 x "+fifties+", 20 x "
                +twenties+", 10 x "+apron+", 5 x "+ five+ ", unpaid amount : " + unpaidAmount;
    }
}
