package com.myfinancial.model.service;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.Expense;
import com.myfinancial.model.domain.entity.User;
import com.myfinancial.model.domain.enums.ExpenseType;
import com.myfinancial.model.domain.enums.ProfileType;
import com.myfinancial.model.repository.CategoryRepository;
import com.myfinancial.model.repository.ExpendeRepository;
import com.myfinancial.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

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

        User userOne = new User("Rafael Marinho", "rafamola@gmail.com", new BCryptPasswordEncoder().encode("123456"),
                null, null, new HashSet<>(Arrays.asList(ProfileType.ADMIN.getCod(), ProfileType.USER.getCod())));
        User userTwo = new User("Corona Vírus", "corona@gmail.com", new BCryptPasswordEncoder().encode("123456"),
                null, null, new HashSet<>(Arrays.asList(ProfileType.USER.getCod())));

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

        LocalDate dateOne = LocalDate.now();

        LocalDate dateTwo = dateOne.plusDays(3);

        Expense expenseOne = new Expense("Faculdade", 450.00D, dateTwo, dateOne, ExpenseType.EXPENSE.getCod(), userOne, categoryOne);
        Expense expenseTwo = new Expense("Curso de Inglês", 179.99D, dateTwo, dateOne, ExpenseType.EXPENSE.getCod(), userOne, categoryOne);
        Expense expenseThree = new Expense("Toca do Lobo", 180.00D, dateTwo, dateOne, ExpenseType.EXPENSE.getCod(), userOne, categoryTwo);
        Expense expenseSeven = new Expense("Salario", 180.00D, null, dateOne, ExpenseType.INCOME.getCod(), userOne, categoryFive);
        Expense expenseFour = new Expense("Faculdade", 320.45D, dateTwo, dateOne, ExpenseType.EXPENSE.getCod(), userTwo, categoryThree);
        Expense expenseFive = new Expense("Moradia", 550.00D, dateTwo, dateOne, ExpenseType.EXPENSE.getCod(), userTwo, categoryFour);
        Expense expenseSix = new Expense("Loja", 600.00D, null, dateOne, ExpenseType.INCOME.getCod(), userTwo, categoryFour);

        expendeRepository.saveAll(Arrays.asList(expenseOne, expenseTwo, expenseThree, expenseSeven, expenseFour, expenseFive, expenseSix));

        expendeRepository.findAll().forEach(expense -> System.out.println(expense));

        categoryRepository.findAll().forEach(category -> System.out.println(category));

        userRepository.findAll().forEach(user -> System.out.println(user));
    }
}
