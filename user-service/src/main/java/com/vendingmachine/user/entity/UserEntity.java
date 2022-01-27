package com.vendingmachine.user.entity;

import com.vendingmachine.user.model.Role;
import lombok.*;
import org.springframework.security.core.userdetails.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;
    private String username;
    private String password;
    private int deposit;
    private String roleFields;
    //private Role roles;

    public UserEntity(Long userId, String username, String password, int deposit, String roleFields) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.deposit = deposit;
        this.roleFields = roleFields;
    }

    public UserEntity(String username, String password, int deposit, String roleFields) {
        this.username = username;
        this.password = password;
        this.deposit = deposit;
        this.roleFields = roleFields;
    }
}
