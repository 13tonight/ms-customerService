package org.tonight.mscustomerservice.handlers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<Map<String, String>> handleArithmeticException(ArithmeticException ex) {
        Map<String, String> ArthimaticBody = new HashMap<>();
        ArthimaticBody.put("417", ex.getMessage());
        return ResponseEntity.status(417).body(ArthimaticBody);
    }
}
