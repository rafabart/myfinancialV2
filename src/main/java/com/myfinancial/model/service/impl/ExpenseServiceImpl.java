package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Expense;
import com.myfinancial.model.domain.entity.User;
import com.myfinancial.model.domain.enums.ExpenseType;
import com.myfinancial.model.domain.request.ExpenseRequest;
import com.myfinancial.model.domain.response.ExpenseResponse;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.repository.ExpendeRepository;
import com.myfinancial.model.service.CategoryService;
import com.myfinancial.model.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpendeRepository expendeRepository;

    @Autowired
    private CategoryService categoryService;


    public ExpenseResponse findById(final Long id) {

        final Expense expense = expendeRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Lançamento"));

        return new ExpenseResponse(expense);
    }


    public List<ExpenseResponse> findAll() {

        final List<Expense> expenseList = expendeRepository.findAll();

        return expenseList.stream().map(expense -> new ExpenseResponse(expense)).collect(Collectors.toList());
    }


    public void delete(final Long id) {

        findById(id);

        expendeRepository.deleteById(id);
    }


    public Long create(final ExpenseRequest expenseRequest) {

        categoryService.findById(expenseRequest.getCategory().getId());

        User user = new User();
        user.setId(1L);

        Expense expense = new Expense(expenseRequest);
        expense.setUser(user);

        return expendeRepository.save(expense).getId();
    }


    public void update(final Long id, final ExpenseRequest expenseRequest) {

        categoryService.findById(expenseRequest.getCategory().getId());

        findById(id);

        User user = new User();
        user.setId(1L);

        Expense expense = new Expense(expenseRequest);
        expense.setId(id);
        expense.setUser(user);

        expendeRepository.save(expense);
    }
}
