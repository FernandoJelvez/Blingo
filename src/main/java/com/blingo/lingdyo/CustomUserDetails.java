package com.blingo.lingdyo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {
    private final User user;

    public CustomUserDetails(User user) { this.user = user; }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Guardar, por si necesitamos agregar roles como Admin.
        return Collections.emptyList();}
    public User getUser() { return user; }
    public void updateUser(User u) {
        this.user.setUsername(u.getUsername());
        this.user.setName(u.getName());
        this.user.setLastname(u.getLastname());
        this.user.setEmail(u.getEmail());
        this.user.setAge(u.getAge());
        this.user.setNative_tonge(u.getNative_tonge());
    }
    public Integer getId() { return this.user.getId(); }
    public void setDescription(String description) { this.user.setDescription(description); }
    @Override
    public String getPassword() { return user.getPswd(); }
    @Override
    public String getUsername() { return user.getUsername(); }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}