package com.myfinancial.model.repository;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query("From Category c WHERE c.customer=:customer AND LOWER(c.name) LIKE LOWER(CONCAT('%', :searchText,'%'))")
    Page<Category> findAllByCustomer(@Param("customer") final Customer customer, @Param("searchText") final String searchText, final Pageable pageable);

    Page<Category> findAllByCustomer(final Customer customer, final Pageable pageable);

    Optional<Category> findByIdAndCustomer(final long id, final Customer customer);

    Optional<Category> findByNameIgnoreCaseAndCustomer(final String name, final Customer customer);
}
