package com.myfinancial.model.service;

import com.myfinancial.model.domain.request.ExpenseRequest;
import com.myfinancial.model.domain.response.ExpenseResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExpenseService {

    ExpenseResponse findById(final Long id);

    Page<ExpenseResponse> findAll(final Integer searchMonth, final Integer searchYear, final Pageable pageable);

    Page<ExpenseResponse> findAll(final String searchText, final Pageable pageable);

    void delete(final Long id);

    Long create(final ExpenseRequest expenseRequest);

    void update(final ExpenseRequest expenseRequest);
}
