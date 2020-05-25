package com.myfinancial.controller.resource;

import com.myfinancial.model.domain.request.CustomerRequest;
import com.myfinancial.model.domain.response.CustomerResponse;
import com.myfinancial.model.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerResource {


    @Autowired
    private CustomerService customerService;


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/admin/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> findById(@PathVariable("id") final Long id) {

        CustomerResponse customerResponse = customerService.findById(id);

        return ResponseEntity.ok(customerResponse);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerResponse> findByIdAndUser(@PathVariable("id") final Long id) {

        CustomerResponse customerResponse = customerService.findByIdAndCustomer(id);

        return ResponseEntity.ok(customerResponse);
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerResponse>> findAll() {

        List<CustomerResponse> customerResponseList = customerService.findAllByUser();

        return ResponseEntity.ok(customerResponseList);
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {

        customerService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> create(@Valid @RequestBody final CustomerRequest customerRequest) {

        final Long id = customerService.create(customerRequest);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping()
    public ResponseEntity<Void> update(@Valid @RequestBody final CustomerRequest customerRequest) {

        customerService.update(customerRequest);

        return ResponseEntity.noContent().build();
    }
}