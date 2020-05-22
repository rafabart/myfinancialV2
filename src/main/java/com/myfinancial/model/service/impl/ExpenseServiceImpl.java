package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Expense;
import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.request.ExpenseRequest;
import com.myfinancial.model.domain.response.ExpenseResponse;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.mapper.ExpenseMapper;
import com.myfinancial.model.repository.ExpendeRepository;
import com.myfinancial.model.service.CategoryService;
import com.myfinancial.model.service.ExpenseService;
import com.myfinancial.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpendeRepository expendeRepository;

    @Autowired
    private ExpenseMapper expenseMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;


    public ExpenseResponse findByIdAndUser(final Long id) {

        final Customer customer = userService.getAuthenticatedUser();

        final Expense expense = expendeRepository.findByIdAndCustomer(id, customer).orElseThrow(() -> new ObjectNotFoundException("Lançamento"));

        return expenseMapper.toReponse(expense);
    }


    public List<ExpenseResponse> findAllByUser() {

        final Customer customer = userService.getAuthenticatedUser();

        final List<Expense> expenseList = expendeRepository.findAllByCustomer(customer);

        return expenseMapper.toResponseList(expenseList);
    }


    @Transactional
    public void delete(final Long id) {

        findByIdAndUser(id);

        expendeRepository.deleteById(id);
    }


    @Transactional
    public Long create(final ExpenseRequest expenseRequest) {

        categoryService.findByIdAndCustomer(expenseRequest.getCategory().getId());

        final Customer customer = userService.getAuthenticatedUser();

        Expense expense = expenseMapper.to(expenseRequest, customer);

        return expendeRepository.save(expense).getId();
    }


    @Transactional
    public void update(final ExpenseRequest expenseRequest) {

        categoryService.findByIdAndCustomer(expenseRequest.getCategory().getId());

        findByIdAndUser(expenseRequest.getId());

        Expense expense = expendeRepository.getOne(expenseRequest.getId());

        expenseMapper.toUpdate(expense, expenseRequest);

        expendeRepository.save(expense);
    }
}
