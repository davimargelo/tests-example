package com.davi.apiapp.controllers.exceptions;

import com.davi.apiapp.services.exceptions.DataIntegrityViolationException;
import com.davi.apiapp.services.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String EMAIL_ALREADY_USED_MESSAGE = "Email já cadastrado no sistema";
    public static final String USER_NOT_FOUND_MESSAGE = "Usuario nao encontrado";

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResponseEntity() throws InterruptedException {
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(
                        new ObjectNotFoundException(USER_NOT_FOUND_MESSAGE),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(USER_NOT_FOUND_MESSAGE, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/user/2", response.getBody().getPath());
        TimeUnit.NANOSECONDS.sleep(1);
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void whenIntegrityViolationExceptionThenReturnAResponseEntity() throws InterruptedException {
        ResponseEntity<StandardError> response = exceptionHandler
                .integrityViolation(
                        new DataIntegrityViolationException(EMAIL_ALREADY_USED_MESSAGE),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(EMAIL_ALREADY_USED_MESSAGE, response.getBody().getError());
        assertEquals(400, response.getBody().getStatus());
        assertNotEquals("/user/2", response.getBody().getPath());
        TimeUnit.NANOSECONDS.sleep(1);
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }
}