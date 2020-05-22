package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Category;
import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.domain.request.CategoryRequest;
import com.myfinancial.model.domain.response.CategoryResponse;
import com.myfinancial.model.exception.CategoryExistingException;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.mapper.CategoryMapper;
import com.myfinancial.model.repository.CategoryRepository;
import com.myfinancial.model.service.CategoryService;
import com.myfinancial.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private UserService userService;


    public CategoryResponse findByIdAndCustomer(final Long id) {

        final Customer customer = userService.getAuthenticatedUser();

        final Category category = categoryRepository.findByIdAndCustomer(id, customer).orElseThrow(() -> new ObjectNotFoundException("Categoria"));

        return categoryMapper.toReponse(category);
    }


    public List<CategoryResponse> findAllByCustomer() {

        final Customer customer = userService.getAuthenticatedUser();

        final List<Category> categoryList = categoryRepository.findAllByCustomer(customer);

        return categoryMapper.toResponseList(categoryList);
    }


    @Transactional
    public Long create(final CategoryRequest categoryRequest) {

        final Customer customer = userService.getAuthenticatedUser();

        categoryRepository.findByNameIgnoreCaseAndCustomer(categoryRequest.getName(), customer)
                .ifPresent(category -> new CategoryExistingException());

        Category category = categoryMapper.to(categoryRequest, customer);

        return categoryRepository.save(category).getId();
    }


    @Transactional
    public void delete(final Long id) {

        findByIdAndCustomer(id);

        categoryRepository.deleteById(id);
    }


    @Transactional
    public void update(final CategoryRequest categoryRequest) {

        findByIdAndCustomer(categoryRequest.getId());

        Category category = categoryRepository.getOne(categoryRequest.getId());

        categoryMapper.toUpdate(category, categoryRequest);

        categoryRepository.save(category);
    }
}