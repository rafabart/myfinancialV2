package com.myfinancial.model.service;

import com.myfinancial.model.entity.Category;
import com.myfinancial.model.entity.Expense;
import com.myfinancial.model.entity.User;
import com.myfinancial.model.enums.ExpenseType;
import com.myfinancial.model.enums.ProfileType;
import com.myfinancial.model.repository.CategoryRepository;
import com.myfinancial.model.repository.ExpendeRepository;
import com.myfinancial.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class DataInitializer implements Runnable {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ExpendeRepository expendeRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public void run() {

        User userOne = new User("Rafael Marinho", "rafamola@gmail.com", "123456", null, null, new HashSet<>(Arrays.asList(ProfileType.ADMIN.getCod())));
        User userTwo = new User("Corona Vírus", "corona@gmail.com", "123456", null, null, new HashSet<>(Arrays.asList(ProfileType.USER.getCod())));

        userOne = userRepository.save(userOne);
        userTwo = userRepository.save(userTwo);


        Category categoryOne = new Category("Educação", userOne, null);
        Category categoryTwo = new Category("Diversão", userOne, null);
        Category categoryFive = new Category("Salario", userOne, null);

        Category categoryThree = new Category("Educação", userTwo, null);
        Category categoryFour = new Category("Aluguel", userTwo, null);

        categoryOne = categoryRepository.save(categoryOne);
        categoryTwo = categoryRepository.save(categoryTwo);
        categoryThree = categoryRepository.save(categoryThree);
        categoryFour = categoryRepository.save(categoryFour);
        categoryFive = categoryRepository.save(categoryFour);


        Expense expenseOne = new Expense("Faculdade", 450.00D, ExpenseType.EXPENSE.getCod(), userOne, categoryOne);
        Expense expenseTwo = new Expense("Curso de Inglês", 179.99D, ExpenseType.EXPENSE.getCod(), userOne, categoryOne);
        Expense expenseThree = new Expense("Toca do Lobo", 180.00D, ExpenseType.EXPENSE.getCod(), userOne, categoryTwo);
        Expense expenseSeven = new Expense("Salario", 180.00D, ExpenseType.INCOME.getCod(), userOne, categoryFive);

        Expense expenseFour = new Expense("Faculdade", 320.45D, ExpenseType.EXPENSE.getCod(), userTwo, categoryThree);
        Expense expenseFive = new Expense("Moradia", 550.00D, ExpenseType.EXPENSE.getCod(), userTwo, categoryFour);
        Expense expenseSix = new Expense("Loja", 600.00D, ExpenseType.INCOME.getCod(), userTwo, categoryFour);

        expendeRepository.saveAll(Arrays.asList(expenseOne, expenseTwo, expenseThree, expenseFour, expenseFive, expenseSix, expenseSeven));

        List<Expense> expenseList = expendeRepository.findAll();

        expenseList.forEach(expense -> System.out.println(expense));
    }
}
