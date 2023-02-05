package com.davi.apiapp.services.impl;

import com.davi.apiapp.domain.User;
import com.davi.apiapp.dto.UserDTO;
import com.davi.apiapp.repositories.UserRepository;
import com.davi.apiapp.services.UserService;
import com.davi.apiapp.services.exceptions.DataIntegrityViolationException;
import com.davi.apiapp.services.exceptions.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public User findById(Integer id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> new ObjectNotFoundException("Usuario nao encontrado"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User create(UserDTO userDTO) {
        findByEmail(userDTO);
        return userRepository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public void findByEmail(UserDTO userDTO) {
        Optional<User> userOptional = userRepository.findByEmail(userDTO.getEmail());

        if (userOptional.isPresent() && !userOptional.get().getId().equals(userDTO.getId())) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }

    @Override
    public User update(UserDTO userDTO) {
        findByEmail(userDTO);
        return userRepository.save(mapper.map(userDTO, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }
}
