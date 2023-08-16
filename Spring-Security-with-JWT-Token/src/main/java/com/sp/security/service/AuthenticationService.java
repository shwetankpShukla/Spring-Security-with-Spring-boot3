package com.sp.security.service;

import com.sp.security.controller.prelogin.AuthenticateRequest;
import com.sp.security.controller.prelogin.AuthenticationResponse;
import com.sp.security.controller.prelogin.RegisterRequest;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest registerRequest);

    public AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest);
}
