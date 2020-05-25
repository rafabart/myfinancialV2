package com.myfinancial.model.service;

import com.myfinancial.model.domain.request.NewPasswordRequest;

public interface AuthService {

    void sendNewPassword(final String email);

    void changePassword(final NewPasswordRequest newPasswordRequest);
}
