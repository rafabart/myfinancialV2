package com.myfinancial.model.service.impl;

import com.myfinancial.model.domain.entity.Customer;
import com.myfinancial.model.repository.CustomerRepository;
import com.myfinancial.model.security.UserSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {

        final Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return new UserSpringSecurity(customer.getId(), customer.getEmail(), customer.getPassword(), customer.getProfiles());
    }
}
