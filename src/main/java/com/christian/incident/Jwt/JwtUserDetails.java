package com.christian.incident.Jwt;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.UUID;

public class JwtUserDetails extends User {
    private final com.christian.incident.entity.User user;

    public JwtUserDetails(com.christian.incident.entity.User user){
        super(
                user.getEmail(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList(user.getRole().name())
        );
        this.user = user;
    }
    public UUID getId(){
        return this.user.getId();
    }

    public String getRole(){
        return this.user.getRole().name();
    }
}
