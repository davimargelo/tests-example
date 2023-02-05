package com.davi.apiapp.services;

import com.davi.apiapp.domain.User;
import com.davi.apiapp.dto.UserDTO;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();

    User create(UserDTO userDTO);

    void findByEmail(UserDTO userDTO);

    User update(UserDTO userDTO);
}
