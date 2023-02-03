package com.davi.apiapp.services;

import com.davi.apiapp.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
