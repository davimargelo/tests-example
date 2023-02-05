package com.davi.apiapp.controllers;

import com.davi.apiapp.domain.User;
import com.davi.apiapp.dto.UserDTO;
import com.davi.apiapp.services.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserControllerTest {

    public static final Integer ID = 1;
    public static final String NAME = "Valdir";
    public static final String EMAIL = "valdir@mail.com";
    public static final String PASSWORD = "123";
    public static final int FIRST_INDEX = 0;
    public static final String EMAIL_ALREADY_USED_MESSAGE = "Email já cadastrado no sistema";
    public static final String USER_NOT_FOUND_MESSAGE = "Usuario nao encontrado";

    @InjectMocks
    private UserController userController;

    @Mock
    private ModelMapper mapper;

    @Mock
    private UserServiceImpl userService;

    private User user;

    private UserDTO userDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser() {
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}