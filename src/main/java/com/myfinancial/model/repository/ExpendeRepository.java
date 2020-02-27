package com.myfinancial.model.repository;

import com.myfinancial.model.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpendeRepository extends JpaRepository<Expense, Long> {
}
