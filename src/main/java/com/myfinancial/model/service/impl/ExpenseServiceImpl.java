package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Expense;
import com.myfinancial.model.domain.entity.User;
import com.myfinancial.model.domain.request.ExpenseRequest;
import com.myfinancial.model.domain.response.ExpenseResponse;
import com.myfinancial.model.exception.ObjectNotFoundException;
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
    private CategoryService categoryService;

    @Autowired
    private UserService userService;


    public ExpenseResponse findByIdAndUser(final Long id) {

        final User user = userService.getAuthenticatedUser();

        final Expense expense = expendeRepository.findByIdAndUser(id, user).orElseThrow(() -> new ObjectNotFoundException("Lançamento"));

        return new ExpenseResponse(expense);
    }


    public List<ExpenseResponse> findAllByUser() {

        final User user = userService.getAuthenticatedUser();

        final List<Expense> expenseList = expendeRepository.findAllByUser(user);

        return expenseList.stream().map(expense -> new ExpenseResponse(expense)).collect(Collectors.toList());
    }


    @Transactional
    public void delete(final Long id) {

        findByIdAndUser(id);

        expendeRepository.deleteById(id);
    }


    @Transactional
    public Long create(final ExpenseRequest expenseRequest) {

        categoryService.findByIdAndUser(expenseRequest.getCategory().getId());

        final User user = userService.getAuthenticatedUser();

        Expense expense = new Expense(expenseRequest);
        expense.setUser(user);

        return expendeRepository.save(expense).getId();
    }


    @Transactional
    public void update(final Long id, final ExpenseRequest expenseRequest) {

        categoryService.findByIdAndUser(expenseRequest.getCategory().getId());

        findByIdAndUser(id);

        Expense expense = expendeRepository.getOne(id);

        expense.updateExpense(expenseRequest);

        expendeRepository.save(expense);
    }
}
