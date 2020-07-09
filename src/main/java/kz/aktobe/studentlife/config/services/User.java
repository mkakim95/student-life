package kz.aktobe.studentlife.config.services;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class User extends org.springframework.security.core.userdetails.User {

    private Long userId;
    private String email;

    public User(String username, String password, Collection<? extends GrantedAuthority> authorities, Long userId, String email) {
        super(username, password, authorities);
        this.userId = userId;
        this.email = email;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
