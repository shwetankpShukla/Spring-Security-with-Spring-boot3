package com.sp.security.controller.demo;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/post-login/demo")
@Log4j2
public class DemoController {

    @GetMapping()
    public ResponseEntity<String> sayHello(){
        log.info("DemoController->sayHello");
        return  ResponseEntity.ok("Hello Sweta in Security Program Your All URI Is Safe And Secure");
    }
}
