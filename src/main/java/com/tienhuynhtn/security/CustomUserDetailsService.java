package com.tienhuynhtn.security;

import com.tienhuynhtn.dto.AccountDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return CustomUserDetails.create(new AccountDTO(
                1,
                "user",
                "tienhuynh-tn-user",
                "User",
                "Đang hoạt động"
        ));
    }

    @Transactional
    public UserDetails loadUserById(int id) {
        return CustomUserDetails.create(new AccountDTO(
                1,
                "user",
                "tienhuynh-tn-user",
                "User",
                "Đang hoạt động"
        ));
    }
}
