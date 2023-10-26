package com.tienhuynhtn.service;

import com.tienhuynhtn.dto.TokenDTO;
import com.tienhuynhtn.request.AuthenticateRequest;

import javax.servlet.http.HttpServletRequest;

public interface AuthenticateService {
    TokenDTO authenticate(HttpServletRequest request, AuthenticateRequest authenticateRequest);
}
