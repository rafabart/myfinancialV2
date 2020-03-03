package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.User;
import com.myfinancial.model.domain.request.UserRequest;
import com.myfinancial.model.domain.response.UserResponse;
import com.myfinancial.model.exception.EmailExistingException;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.repository.UserRepository;
import com.myfinancial.model.service.EmailService;
import com.myfinancial.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserResponse findById(final Long id) {

        final User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário"));

        return new UserResponse(user);
    }


    public List<UserResponse> findAll() {

        List<User> userList = userRepository.findAll();

        return userList.stream().map(user -> new UserResponse(user)).collect(Collectors.toList());
    }


    @Transactional
    public void delete(final Long id) {

        findById(id);

        userRepository.deleteById(id);
    }


    @Transactional
    public Long create(final UserRequest userRequest) {

        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new EmailExistingException();
        }

        User user = new User(userRequest);
        user.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));

        final Long id = userRepository.save(user).getId();

        emailService.sendAccountCreatedConfirmationEmail(userRequest);

        return id;
    }


    @Transactional
    public void update(final Long id, final UserRequest userRequest) {

        findById(id);

        Optional<User> userOptional = userRepository.findByEmail(userRequest.getEmail());

        if (userOptional.isPresent() && userOptional.get().getId() != id) {
            throw new EmailExistingException();
        }

        User user = new User(userRequest);
        user.setId(id);

        userRepository.save(user);
    }
}
