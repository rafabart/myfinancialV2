package com.myfinancial.model.service;

import com.myfinancial.model.domain.request.CategoryRequest;
import com.myfinancial.model.domain.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategoryResponse findById(final Long id);

    Page<CategoryResponse> findAll(final String searchText, final Pageable pageable);

    Page<CategoryResponse> findAll(final Pageable pageable);

    Long create(final CategoryRequest categoryRequest);

    void delete(final Long id);

    void update(CategoryRequest categoryRequest);
}