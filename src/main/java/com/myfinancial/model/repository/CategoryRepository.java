package com.myfinancial.model.repository;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByUser(final User user);

    Optional<Category> findByIdAndUser(final long id, final User user);

    Optional<Category> findByNameIgnoreCaseAndUser(final String name, final User user);
}
