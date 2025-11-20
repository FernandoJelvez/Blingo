package com.blingo.lingdyo.configuration;

import com.blingo.lingdyo.User;
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
    public void updateUser(User user) {
        this.user.setId(user.getId());
        this.user.setName(user.getName());
        this.user.setLastname(user.getLastname());
        this.user.setEmail(user.getEmail());
        this.user.setAge(user.getAge());
        this.user.setNative_tonge(user.getNative_tonge());}
    public void setDescription(String description) { this.user.setDescription(description); }
    @Override
    public String getPassword() { return user.getPswd(); }
    @Override
    public String getUsername() { return user.getId(); }
    @Override
    public boolean isAccountNonExpired() { return true; }
    @Override
    public boolean isAccountNonLocked() { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled() { return true; }
}