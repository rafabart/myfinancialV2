package com.myfinancial.model.repository;

import com.myfinancial.model.domain.entity.Expense;
import com.myfinancial.model.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpendeRepository extends JpaRepository<Expense, Long> {

    Optional<Expense> findByIdAndUser(final Long id, final User user);

    List<Expense> findAllByUser(final User user);
}
