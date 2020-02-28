package com.myfinancial.controller.resource;

import com.myfinancial.model.domain.request.CategoryRequest;
import com.myfinancial.model.domain.response.CategoryResponse;
import com.myfinancial.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<CategoryResponse> findById(@PathVariable("id") final Long id) {

        final CategoryResponse categoryResponse = categoryService.findById(id);

        return ResponseEntity.ok(categoryResponse);
    }


    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<CategoryResponse>> findAll() {

        final List<CategoryResponse> categoryResponseList = categoryService.findAll();

        return ResponseEntity.ok(categoryResponseList);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {

        categoryService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<URI> create(@Valid @RequestBody final CategoryRequest categoryRequest) {

        final Long id = categoryService.create(categoryRequest);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/{id}", consumes = {"application/json"})
    public ResponseEntity<Void> update(@PathVariable("id") final Long id,
                                       @Valid @RequestBody final CategoryRequest categoryRequest) {

        categoryService.update(id, categoryRequest);

        return ResponseEntity.noContent().build();
    }
}
