package com.myfinancial.model.repository;

import com.myfinancial.model.domain.entity.Expense;
import com.myfinancial.model.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpendeRepository extends JpaRepository<Expense, Long> {

    Optional<Expense> findByIdAndCustomer(final Long id, final Customer customer);

    List<Expense> findAllByCustomer(final Customer customer);
}
