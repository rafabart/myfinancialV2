package com.myfinancial.model.domain.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {

    private String email;

    private String password;
}
