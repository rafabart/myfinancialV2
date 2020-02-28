package com.myfinancial.model.service;

import com.myfinancial.model.domain.request.CategoryRequest;
import com.myfinancial.model.domain.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse findById(final Long id);

    List<CategoryResponse> findAll();

    Long create(final CategoryRequest categoryRequest);

    void delete(final Long id);

    void update(final Long id, CategoryRequest categoryRequest);
}
