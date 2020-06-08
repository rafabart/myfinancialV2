package com.myfinancial.model.service;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.entity.Expense;
import com.myfinancial.model.domain.enums.ExpenseType;
import com.myfinancial.model.domain.enums.ProfileType;
import com.myfinancial.model.repository.CategoryRepository;
import com.myfinancial.model.repository.CustomerRepository;
import com.myfinancial.model.repository.ExpendeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;

@Service
public class DataInitializer implements Runnable {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ExpendeRepository expendeRepository;

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public void run() {

        Customer customerOne = new Customer("Rafael Marinho", "rafamola@gmail.com", new BCryptPasswordEncoder().encode("123456"), ProfileType.ADMIN);
        Customer customerTwo = new Customer("Corona Vírus", "corona@gmail.com", new BCryptPasswordEncoder().encode("123456"), ProfileType.USER);

        customerOne = customerRepository.save(customerOne);
        customerTwo = customerRepository.save(customerTwo);


        Category categoryOne = new Category("Educação", customerOne);
        Category categoryTwo = new Category("Diversão", customerOne);
        Category categoryFive = new Category("Salario", customerOne);


        Category categoryThree = new Category("Educação", customerTwo);
        Category categoryFour = new Category("Aluguel", customerTwo);

        categoryOne = categoryRepository.save(categoryOne);
        categoryTwo = categoryRepository.save(categoryTwo);
        categoryThree = categoryRepository.save(categoryThree);
        categoryFour = categoryRepository.save(categoryFour);
        categoryFive = categoryRepository.save(categoryFour);


        for (int i = 0; i < 100; i++) {
            Category category = new Category();
            category.setCustomer(customerOne);
            category.setName("Categoria" + i);

            categoryRepository.save(category);
        }

        LocalDate dateOne = LocalDate.now();
        LocalDate dateTwo = dateOne.plusDays(3);

        Expense expenseOne = new Expense("Faculdade", 450.00D, dateTwo, dateOne, ExpenseType.EXPENSE, customerOne, categoryOne);
        Expense expenseTwo = new Expense("Curso de Inglês", 179.99D, dateTwo, dateOne, ExpenseType.EXPENSE, customerOne, categoryOne);
        Expense expenseThree = new Expense("Toca do Lobo", 180.00D, dateTwo, dateOne, ExpenseType.EXPENSE, customerOne, categoryTwo);
        Expense expenseSeven = new Expense("Salario", 180.00D, null, dateOne, ExpenseType.INCOME, customerOne, categoryFive);
        Expense expenseFour = new Expense("Faculdade", 320.45D, dateTwo, dateOne, ExpenseType.EXPENSE, customerTwo, categoryThree);
        Expense expenseFive = new Expense("Moradia", 550.00D, dateTwo, dateOne, ExpenseType.EXPENSE, customerTwo, categoryFour);
        Expense expenseSix = new Expense("Loja", 600.00D, null, dateOne, ExpenseType.INCOME, customerTwo, categoryFour);

        expendeRepository.saveAll(Arrays.asList(expenseOne, expenseTwo, expenseThree, expenseSeven, expenseFour, expenseFive, expenseSix));
    }
}
