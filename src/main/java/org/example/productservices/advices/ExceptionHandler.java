package org.example.productservices.advices;

import org.example.productservices.exceptions.ProductOutOfStock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException e){
        return new ResponseEntity<>("Something went wrong", HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ProductOutOfStock.class)
    public ResponseEntity<String> handleProductOutOfStock(ProductOutOfStock e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
