package com.myfinancial.model.service;

import com.myfinancial.model.domain.request.ExpenseRequest;
import com.myfinancial.model.domain.response.ExpenseResponse;

import java.util.List;

public interface ExpenseService {

    ExpenseResponse findByIdAndUser(final Long id);

    List<ExpenseResponse> findAllByUser();

    void delete(final Long id);

    Long create(final ExpenseRequest expenseRequest);

    void update(final ExpenseRequest expenseRequest);
}
