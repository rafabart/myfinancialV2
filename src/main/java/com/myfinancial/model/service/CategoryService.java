package com.myfinancial.model.service;

import com.myfinancial.model.domain.request.CategoryRequest;
import com.myfinancial.model.domain.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse findByIdAndCustomer(final Long id);

    List<CategoryResponse> findAllByCustomer();

    Long create(final CategoryRequest categoryRequest);

    void delete(final Long id);

    void update(CategoryRequest categoryRequest);
}