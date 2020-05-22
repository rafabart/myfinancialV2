package com.myfinancial.model.service;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.request.UserRequest;
import com.myfinancial.model.domain.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse findById(final Long id);

    UserResponse findByIdAndUser(final Long id);

    List<UserResponse> findAll();

    void delete(final Long id);

    Long create(final UserRequest userRequest);

    void update(final Long id, final UserRequest userRequest);

    Customer getAuthenticatedUser();
}
