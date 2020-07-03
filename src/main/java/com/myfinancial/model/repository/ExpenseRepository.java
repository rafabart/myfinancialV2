package com.myfinancial.model.repository;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query("From Expense e WHERE e.customer=:customer AND LOWER(e.description) LIKE LOWER(CONCAT('%', :searchText,'%'))")
    Page<Expense> findAllByCustomer(@Param("customer") final Customer customer, @Param("searchText") final String searchText, final Pageable pageable);

    @Query("From Expense e WHERE e.customer=:customer AND MONTH(e.dueDate) =:searchMonth AND YEAR(e.dueDate) =:searchYear")
    Page<Expense> findAllByCustomer(final Customer customer, final Integer searchMonth, final Integer searchYear, final Pageable pageable);

    Optional<Expense> findByIdAndCustomer(final Long id, final Customer customer);
}
