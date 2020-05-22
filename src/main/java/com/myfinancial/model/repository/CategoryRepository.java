package com.myfinancial.model.repository;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByCustomer(final Customer customer);

    Optional<Category> findByIdAndCustomer(final long id, final Customer customer);

    Optional<Category> findByNameIgnoreCaseAndCustomer(final String name, final Customer customer);
}
