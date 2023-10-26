package com.tienhuynhtn.service.serviceImpl;

import com.tienhuynhtn.dto.TokenDTO;
import com.tienhuynhtn.enums.DoctorAnywhereErrorCodeEnum;
import com.tienhuynhtn.exception.BadRequestException;
import com.tienhuynhtn.exception.ForbiddenException;
import com.tienhuynhtn.exception.NotFoundException;
import com.tienhuynhtn.request.AuthenticateRequest;
import com.tienhuynhtn.security.CustomUserDetailsService;
import com.tienhuynhtn.security.TokenProvider;
import com.tienhuynhtn.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public TokenDTO authenticate(HttpServletRequest request, AuthenticateRequest authenticateRequest) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(authenticateRequest.getUsername());

        if (!userDetails.getUsername().equals(authenticateRequest.getUsername()))
            throw new NotFoundException(DoctorAnywhereErrorCodeEnum.NOT_FOUND_ACCOUNT, DoctorAnywhereErrorCodeEnum.NOT_FOUND_ACCOUNT.getMessage());

        if (!userDetails.getPassword().equals(authenticateRequest.getPassword()))
            throw new BadRequestException(DoctorAnywhereErrorCodeEnum.INVALID_PASSWORD, DoctorAnywhereErrorCodeEnum.INVALID_PASSWORD.getMessage());

        if (!userDetails.isAccountNonLocked()) {
            throw new ForbiddenException(DoctorAnywhereErrorCodeEnum.UNAUTHORIZED, DoctorAnywhereErrorCodeEnum.UNAUTHORIZED.getMessage());
        }

        UsernamePasswordAuthenticationToken
                authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Return jwt token to User
        String jwt = tokenProvider.generateToken(authentication);
        return new TokenDTO(jwt);
    }
}
