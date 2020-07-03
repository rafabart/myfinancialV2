package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.entity.Expense;
import com.myfinancial.model.domain.request.ExpenseRequest;
import com.myfinancial.model.domain.response.ExpenseResponse;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.mapper.ExpenseMapper;
import com.myfinancial.model.repository.ExpenseRepository;
import com.myfinancial.model.service.CategoryService;
import com.myfinancial.model.service.CustomerService;
import com.myfinancial.model.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseMapper expenseMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CustomerService customerService;


    public ExpenseResponse findById(final Long id) {

        final Customer customer = customerService.getAuthenticatedUser();

        final Expense expense = expenseRepository.findByIdAndCustomer(id, customer).orElseThrow(() -> new ObjectNotFoundException("Lançamento"));

        return expenseMapper.toReponse(expense);
    }


    public Page<ExpenseResponse> findAll(final String searchText, final Pageable pageable) {

        final Customer customer = customerService.getAuthenticatedUser();

        return expenseRepository.findAllByCustomer(customer, searchText, pageable).map(expenseMapper::toReponse);
    }


    public Page<ExpenseResponse> findAll(final Integer searchMonth, final Integer searchYear, final Pageable pageable) {

        final Customer customer = customerService.getAuthenticatedUser();

        return expenseRepository.findAllByCustomer(customer, searchMonth, searchYear, pageable).map(expenseMapper::toReponse);
    }


    @Transactional
    public void delete(final Long id) {

        findById(id);

        expenseRepository.deleteById(id);
    }


    @Transactional
    public Long create(final ExpenseRequest expenseRequest) {

        categoryService.findById(expenseRequest.getCategory().getId());

        final Customer customer = customerService.getAuthenticatedUser();

        Expense expense = expenseMapper.to(expenseRequest, customer);

        return expenseRepository.save(expense).getId();
    }


    @Transactional
    public void update(final ExpenseRequest expenseRequest) {

        categoryService.findById(expenseRequest.getCategory().getId());

        findById(expenseRequest.getId());

        Expense expense = expenseRepository.getOne(expenseRequest.getId());

        expenseMapper.toUpdate(expense, expenseRequest);

        expenseRepository.save(expense);
    }
}
