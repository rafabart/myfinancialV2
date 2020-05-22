package com.myfinancial.controller.resource;

import com.myfinancial.model.domain.request.CategoryRequest;
import com.myfinancial.model.domain.response.CategoryResponse;
import com.myfinancial.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryResource {

    @Autowired
    private CategoryService categoryService;


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> findByIdAndUser(@PathVariable("id") final Long id) {

        final CategoryResponse categoryResponse = categoryService.findByIdAndCustomer(id);

        return ResponseEntity.ok(categoryResponse);
    }


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CategoryResponse>> findAllByUser() {

        final List<CategoryResponse> categoryResponseList = categoryService.findAllByCustomer();

        return ResponseEntity.ok(categoryResponseList);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {

        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> create(@Valid @RequestBody final CategoryRequest categoryRequest) {

        final Long id = categoryService.create(categoryRequest);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> update(@Valid @RequestBody final CategoryRequest categoryRequest) {

        categoryService.update(categoryRequest);

        return ResponseEntity.noContent().build();
    }
}
