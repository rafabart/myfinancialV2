package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.User;
import com.myfinancial.model.domain.request.CategoryRequest;
import com.myfinancial.model.domain.response.CategoryResponse;
import com.myfinancial.model.exception.CategoryExistingException;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.repository.CategoryRepository;
import com.myfinancial.model.service.CategoryService;
import com.myfinancial.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserService userService;


    public CategoryResponse findByIdAndUser(final Long id) {

        final User user = userService.getAuthenticatedUser();

        final Category category = categoryRepository.findByIdAndUser(id, user).orElseThrow(() -> new ObjectNotFoundException("Categoria"));

        return new CategoryResponse(category);
    }


    public List<CategoryResponse> findAllByUser() {

        final User user = userService.getAuthenticatedUser();

        final List<Category> categoryList = categoryRepository.findAllByUser(user);

        return categoryList.stream().map(category -> new CategoryResponse(category)).collect(Collectors.toList());
    }


    @Transactional
    public Long create(final CategoryRequest categoryRequest) {

        final User user = userService.getAuthenticatedUser();

        if (categoryRepository.findByNameIgnoreCaseAndUser(categoryRequest.getName(), user).isPresent()) {
            throw new CategoryExistingException();
        }

        Category category = new Category(categoryRequest);

        category.setUser(user);

        return categoryRepository.save(category).getId();
    }


    @Transactional
    public void delete(final Long id) {

        findByIdAndUser(id);

        categoryRepository.deleteById(id);
    }


    @Transactional
    public void update(final Long id, final CategoryRequest categoryRequest) {

        findByIdAndUser(id);

        Category category = categoryRepository.getOne(id);

        category.updateCategory(categoryRequest);

        categoryRepository.save(category);
    }
}
