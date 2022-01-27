package com.vendingmachine.user.service;

import com.vendingmachine.user.entity.UserEntity;
import com.vendingmachine.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserAutService implements UserDetailsService
{

    @Autowired
    UserRepository userRepository;
    PasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
    {
        UserEntity user = userRepository.findByUsername(username);
        User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        builder.password(user.getPassword());
       // builder.authorities(user.getRoles());
        return builder.build();
    }

    public boolean save(UserEntity user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       userRepository.save(user);
        return true;
    }
}