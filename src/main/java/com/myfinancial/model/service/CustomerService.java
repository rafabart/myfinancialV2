package com.myfinancial.model.service;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.request.CustomerRequest;
import com.myfinancial.model.domain.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse findById(final Long id);

    CustomerResponse findByIdAndCustomer(final Long id);

    List<CustomerResponse> findAllByUser();

    void delete(final Long id);

    Long create(final CustomerRequest customerRequest);

    void update(final CustomerRequest customerRequest);

    Customer getAuthenticatedUser();
}
