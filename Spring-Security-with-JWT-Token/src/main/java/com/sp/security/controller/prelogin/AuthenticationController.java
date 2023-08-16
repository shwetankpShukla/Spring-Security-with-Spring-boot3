package com.sp.security.controller.prelogin;

import com.sp.security.service.AuthenticationService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/pre-login/auth")
@AllArgsConstructor
@Log4j2
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;


    @PostMapping(path = "/register",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        AuthenticationResponse response =authenticationService.register(registerRequest);
        return  ResponseEntity.ok(response);
    }

    @PostMapping(path = "/authenticate",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticateRequest authenticateRequest){
        log.info("AuthenticationController->authenticate");
        AuthenticationResponse response =authenticationService.authenticate(authenticateRequest);
        return  ResponseEntity.ok(response);
    }
}
