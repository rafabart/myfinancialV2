package com.myfinancial.controller.resource;

import com.myfinancial.model.domain.request.ExpenseRequest;
import com.myfinancial.model.domain.response.ExpenseResponse;
import com.myfinancial.model.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseResource {

    @Autowired
    private ExpenseService expenseService;


    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<ExpenseResponse> findByIdAndUser(@PathVariable("id") final Long id) {

        final ExpenseResponse expenseResponse = expenseService.findByIdAndUser(id);

        return ResponseEntity.ok(expenseResponse);
    }


    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<ExpenseResponse>> findAllByUser() {

        final List<ExpenseResponse> expenseResponseList = expenseService.findAllByUser();

        return ResponseEntity.ok(expenseResponseList);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {

        expenseService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<URI> create(@Valid @RequestBody final ExpenseRequest expenseRequest) {

        final Long id = expenseService.create(expenseRequest);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping(value = "/{id}", consumes = {"application/json"})
    public ResponseEntity<Void> update(@PathVariable("id") final Long id,
                                       @Valid @RequestBody ExpenseRequest expenseRequest) {

        expenseService.update(id, expenseRequest);

        return ResponseEntity.noContent().build();
    }
}
