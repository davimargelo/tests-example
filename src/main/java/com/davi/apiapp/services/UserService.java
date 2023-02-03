package com.davi.apiapp.services;

import com.davi.apiapp.domain.User;

public interface UserService {

    User findById(Integer id);
}
