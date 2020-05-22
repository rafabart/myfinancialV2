package com.myfinancial.controller.resource;

import com.myfinancial.model.domain.request.EmailRequest;
import com.myfinancial.model.security.JWTUtil;
import com.myfinancial.model.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthService authService;


    @PostMapping(value = "/forgot", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody final EmailRequest emailRequest) {

        authService.sendNewPassword(emailRequest.getEmail());

        return ResponseEntity.noContent().build();
    }
}
