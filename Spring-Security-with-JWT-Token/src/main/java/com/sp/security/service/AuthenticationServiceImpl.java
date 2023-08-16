package com.sp.security.service;

import com.sp.security.config.JWTService;
import com.sp.security.controller.prelogin.AuthenticateRequest;
import com.sp.security.controller.prelogin.AuthenticationResponse;
import com.sp.security.controller.prelogin.RegisterRequest;
import com.sp.security.model.Role;
import com.sp.security.model.User;
import com.sp.security.repositry.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Log4j2
public class AuthenticationServiceImpl implements AuthenticationService{

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTService jwtService;
    private AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .userId(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName()).role(Role.USER)
                .email(registerRequest.getEmail()).build();
        userRepository.save(user);
        var token =jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();

    }



    @Override
    public AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest) {
        log.info("AuthenticationServiceImpl -> authenticate-> userId:{},Password:{}",authenticateRequest.getUserId(),authenticateRequest.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getUserId(),authenticateRequest.getPassword()));
        var user =userRepository.findByUserId(authenticateRequest.getUserId()).orElseThrow() ;
        var token =jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(token).build();
    }
}
