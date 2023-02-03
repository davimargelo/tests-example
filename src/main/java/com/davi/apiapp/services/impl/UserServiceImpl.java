package com.davi.apiapp.services.impl;

import com.davi.apiapp.domain.User;
import com.davi.apiapp.repositories.UserRepository;
import com.davi.apiapp.services.UserService;
import com.davi.apiapp.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new ObjectNotFoundException("Usuario nao encontrado"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
