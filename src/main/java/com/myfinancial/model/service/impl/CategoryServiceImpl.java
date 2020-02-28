package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.User;
import com.myfinancial.model.domain.request.CategoryRequest;
import com.myfinancial.model.domain.response.CategoryResponse;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.repository.CategoryRepository;
import com.myfinancial.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;


    public CategoryResponse findById(final Long id) {

        final Category category = categoryRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Categoria"));

        return new CategoryResponse(category);
    }


    public List<CategoryResponse> findAll() {

        final List<Category> categoryList = categoryRepository.findAll();

        return categoryList.stream().map(category -> new CategoryResponse(category)).collect(Collectors.toList());
    }


    public Long create(final CategoryRequest categoryRequest) {

        Category category = new Category(categoryRequest);

        User user = new User();
        user.setId(1L);

        category.setUser(user);

        return categoryRepository.save(category).getId();
    }


    public void delete(final Long id) {

        findById(id);

        categoryRepository.deleteById(id);
    }


    public void update(final Long id, CategoryRequest categoryRequest) {

        findById(id);

        User user = new User();
        user.setId(1L);

        Category category = new Category(categoryRequest);
        category.setId(id);
        category.setUser(user);

        categoryRepository.save(category);
    }
}
