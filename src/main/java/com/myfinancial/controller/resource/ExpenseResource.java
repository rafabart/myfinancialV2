package com.myfinancial.controller.resource;

import com.myfinancial.model.domain.request.ExpenseRequest;
import com.myfinancial.model.domain.response.ExpenseResponse;
import com.myfinancial.model.service.ExpenseService;
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
@RequestMapping(value = "/expenses", produces = MediaType.APPLICATION_JSON_VALUE)
public class ExpenseResource {

    @Autowired
    private ExpenseService expenseService;


    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponse> findById(@PathVariable("id") final Long id) {

        final ExpenseResponse expenseResponse = expenseService.findById(id);

        return ResponseEntity.ok(expenseResponse);
    }


    @GetMapping("/find/{searchText}")
    public ResponseEntity<Page<ExpenseResponse>> findAll(@PathVariable("searchText") final String searchText,
                                                         final Pageable pageable) {

        final Page<ExpenseResponse> expenseResponseList = expenseService.findAll(searchText, pageable);

        return ResponseEntity.ok(expenseResponseList);
    }


    @GetMapping("/{searchMonth}/{searchYear}")
    public ResponseEntity<Page<ExpenseResponse>> findAll(@PathVariable("searchMonth") final Integer searchMonth,
                                                         @PathVariable("searchYear") final Integer searchYear,
                                                         final Pageable pageable) {

        final Page<ExpenseResponse> expenseResponseList = expenseService.findAll(searchMonth, searchYear, pageable);

        return ResponseEntity.ok(expenseResponseList);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {

        expenseService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping
    public ResponseEntity<URI> create(@Valid @RequestBody final ExpenseRequest expenseRequest) {

        final Long id = expenseService.create(expenseRequest);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping
    public ResponseEntity<Void> update(@Valid @RequestBody ExpenseRequest expenseRequest) {

        expenseService.update(expenseRequest);

        return ResponseEntity.noContent().build();
    }
}
