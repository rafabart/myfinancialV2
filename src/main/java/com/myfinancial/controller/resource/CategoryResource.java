package com.myfinancial.controller.resource;

import com.myfinancial.model.domain.request.CategoryRequest;
import com.myfinancial.model.domain.response.CategoryResponse;
import com.myfinancial.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> findByIdAndUser(@PathVariable("id") final Long id) {

        final CategoryResponse categoryResponse = categoryService.findByIdAndCustomer(id);

        return ResponseEntity.ok(categoryResponse);
    }


    @GetMapping("/find/{searchText}")
    public ResponseEntity<Page<CategoryResponse>> findAllByUser(@PathVariable("searchText") final String searchText,
                                                                final Pageable pageable) {

        final Page<CategoryResponse> categoryResponseList = categoryService.findAllByCustomer(searchText, pageable);

        return ResponseEntity.ok(categoryResponseList);
    }


    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> findAllByUser(final Pageable pageable) {

        final Page<CategoryResponse> categoryResponseList = categoryService.findAllByCustomer(pageable);

        return ResponseEntity.ok(categoryResponseList);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {

        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<URI> create(@Valid @RequestBody final CategoryRequest categoryRequest) {

        final Long id = categoryService.create(categoryRequest);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody final CategoryRequest categoryRequest) {

        categoryService.update(categoryRequest);

        return ResponseEntity.noContent().build();
    }
}
