package com.tienhuynhtn.security;

import com.tienhuynhtn.constant.StatusConstant;
import com.tienhuynhtn.dto.AccountDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CustomUserDetails implements UserDetails {
    @Getter
    private int id;
    private String username;
    private String password;
    private String role;
    private String status;
    private Collection<? extends GrantedAuthority> authorities;
    private Map<String, Object> attributes;

    public CustomUserDetails(int id, String username, String password, String role, String status, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
        this.authorities = authorities;
    }

    public static CustomUserDetails create(AccountDTO accountDTO) {
        List<GrantedAuthority> authorities = Collections.
                singletonList(new SimpleGrantedAuthority("ROLE_" + accountDTO.getRole()));

        return new CustomUserDetails(
                accountDTO.getId(),
                accountDTO.getUsername(),
                accountDTO.getPassword(),
                accountDTO.getRole(),
                accountDTO.getStatus(),
                authorities
        );
    }

    public static CustomUserDetails create(AccountDTO accountDTO, Map<String, Object> attributes) {
        CustomUserDetails customUserDetails = CustomUserDetails.create(accountDTO);
        customUserDetails.setAttributes(attributes);
        return customUserDetails;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status.equals(StatusConstant.ACTIVE);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getRole() {
        return role;
    }
}
