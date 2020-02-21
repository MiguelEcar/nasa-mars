package com.ecarsm.preoday.mars.security;

import com.ecarsm.preoday.mars.entity.LocalUser;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserLogin extends User {

    private static final long serialVersionUID = 1L;

    @Getter
    private LocalUser user;

    public UserLogin(LocalUser user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.user = user;
    }
}
