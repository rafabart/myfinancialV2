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
import com.myfinancial.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CategoryServiceImpl implements CategoryService {


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private CustomerService customerService;


    public CategoryResponse findById(final Long id) {

        final Customer customer = customerService.getAuthenticatedUser();

        final Category category = categoryRepository.findByIdAndCustomer(id, customer).orElseThrow(() -> new ObjectNotFoundException("Categoria"));

        return categoryMapper.toReponse(category);
    }


    public Page<CategoryResponse> findAll(final String searchText, final Pageable pageable) {

        final Customer customer = customerService.getAuthenticatedUser();

        return categoryRepository.findAllByCustomer(customer, searchText, pageable).map(categoryMapper::toReponse);
    }


    public Page<CategoryResponse> findAll(final Pageable pageable) {

        final Customer customer = customerService.getAuthenticatedUser();

        return categoryRepository.findAllByCustomer(customer, pageable).map(categoryMapper::toReponse);
    }


    @Transactional
    public Long create(final CategoryRequest categoryRequest) {

        final Customer customer = customerService.getAuthenticatedUser();

        categoryRepository.findByNameIgnoreCaseAndCustomer(categoryRequest.getName(), customer).ifPresent(category -> {
            throw new CategoryExistingException();
        });


        Category category = categoryMapper.to(categoryRequest, customer);

        return categoryRepository.save(category).getId();
    }


    @Transactional
    public void delete(final Long id) {

        this.findById(id);

        categoryRepository.deleteById(id);
    }


    @Transactional
    public void update(final CategoryRequest categoryRequest) {

        this.findById(categoryRequest.getId());

        Category category = categoryRepository.getOne(categoryRequest.getId());

        categoryMapper.toUpdate(category, categoryRequest);

        categoryRepository.save(category);
    }
}