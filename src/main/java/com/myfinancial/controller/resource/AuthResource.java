package com.myfinancial.controller.resource;

import com.myfinancial.model.domain.request.EmailRequest;
import com.myfinancial.model.domain.request.NewPasswordRequest;
import com.myfinancial.model.security.JWTUtil;
import com.myfinancial.model.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private AuthService authService;


    @PostMapping(value = "/forgot", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody final EmailRequest emailRequest) {

        authService.sendNewPassword(emailRequest.getEmail());

        return ResponseEntity.noContent().build();
    }


    @PutMapping(value = "/newPassword", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> newPassword(@Valid @RequestBody final NewPasswordRequest newPasswordRequest) {

        authService.changePassword(newPasswordRequest);

        return ResponseEntity.noContent().build();
    }
}
