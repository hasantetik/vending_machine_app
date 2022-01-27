package com.vendingmachine.user.model;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority
{
    private static final long serialVersionUID = 3661468982812594323L;

    private String name;

    @Override
    public String getAuthority()
    {
        return name;
    }
}