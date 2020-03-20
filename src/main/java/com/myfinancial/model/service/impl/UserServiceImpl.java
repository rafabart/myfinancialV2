package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.User;
import com.myfinancial.model.domain.enums.ProfileType;
import com.myfinancial.model.domain.request.UserRequest;
import com.myfinancial.model.domain.response.UserResponse;
import com.myfinancial.model.exception.AuthorizationException;
import com.myfinancial.model.exception.EmailExistingException;
import com.myfinancial.model.exception.ObjectNotFoundException;
import com.myfinancial.model.repository.UserRepository;
import com.myfinancial.model.security.UserSpringSecurity;
import com.myfinancial.model.service.EmailService;
import com.myfinancial.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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


    public UserResponse findByIdAndUser(final Long id) {

        final User userAuthenticated = getAuthenticatedUser();

        final User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário"));

        if (!user.getId().equals(userAuthenticated.getId())) {
            throw new AuthorizationException();
        }

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

//        try {
            emailService.sendAccountCreatedConfirmationEmail(userRequest);
//        } catch (Exception e) {
//            throw new EmailSenderException("Não foi possível enviar o email, o novo usuário não foi criado!");
//        }

        return id;
    }


    @Transactional
    public void update(final Long id, final UserRequest userRequest) {

        findById(id);

        User user = getAuthenticatedUser();

        if (user.getId() != id && !user.getProfileList().contains(ProfileType.ADMIN.getCod())) {
            throw new AuthorizationException();
        }

        if (!user.getProfileList().contains(ProfileType.ADMIN.getCod()) && userRequest.getProfileListSring().contains(ProfileType.ADMIN.getName())) {
            throw new AuthorizationException();
        }

        Optional<User> userOptional = userRepository.findByEmail(userRequest.getEmail());

        if (userOptional.isPresent() && userOptional.get().getId() != id) {
            throw new EmailExistingException();
        }

        if (!user.getPassword().equals(userRequest.getPassword())) {
            userRequest.setPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        }

        user = userRepository.getOne(id);
        user.updateUser(userRequest);

        userRepository.save(user);
    }


    public User getAuthenticatedUser() {


        try {
            final UserSpringSecurity userSpringSecurity = (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            final User user = new User(userSpringSecurity);

            return user;

        } catch (Exception e) {
            throw new AuthorizationException();
        }
    }
}
