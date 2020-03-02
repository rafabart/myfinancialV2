package com.myfinancial.controller.resource;

import com.myfinancial.model.domain.request.UserRequest;
import com.myfinancial.model.domain.response.UserResponse;
import com.myfinancial.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserResource {


    @Autowired
    private UserService userService;


    @GetMapping(value = "/{id}", produces = {"application/json"})
    public ResponseEntity<UserResponse> findById(@PathVariable("id") final Long id) {

        UserResponse userResponse = userService.findById(id);

        return ResponseEntity.ok(userResponse);
    }


    @GetMapping(produces = {"application/json"})
    public ResponseEntity<List<UserResponse>> findAll() {

        List<UserResponse> userResponseList = userService.findAll();

        return ResponseEntity.ok(userResponseList);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {

        userService.delete(id);

        return ResponseEntity.noContent().build();
    }


    @PostMapping(consumes = {"application/json"})
    public ResponseEntity<URI> create(@Valid @RequestBody final UserRequest userRequest) {

        final Long id = userService.create(userRequest);

        final URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(uri).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") final Long id, @Valid @RequestBody final UserRequest userRequest) {

        userService.update(id, userRequest);

        return ResponseEntity.noContent().build();
    }
}