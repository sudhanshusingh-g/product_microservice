package org.example.productservices.advices;

import org.example.productservices.dtos.ExceptionDTO;
import org.example.productservices.exceptions.ProductNotFound;
import org.example.productservices.exceptions.ProductOutOfStock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    // Handle ProductNotFound Exception
    @org.springframework.web.bind.annotation.ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ExceptionDTO> handleProductNotFoundException(ProductNotFound e) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(e.getMessage());
        dto.setStatus("PRODUCT_NOT_FOUND");
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    // Handle ProductOutOfStock Exception
    @org.springframework.web.bind.annotation.ExceptionHandler(ProductOutOfStock.class)
    public ResponseEntity<ExceptionDTO> handleProductOutOfStockException(ProductOutOfStock e) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(e.getMessage());
        dto.setStatus("PRODUCT_OUT_OF_STOCK");
        return new ResponseEntity<>(dto, HttpStatus.BAD_REQUEST);
    }

    // Handle Generic Runtime Exceptions
    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionDTO> handleRuntimeException(RuntimeException e) {
        // Return sanitized response
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage("An unexpected error occurred. Please try again later.");
        dto.setStatus("INTERNAL_SERVER_ERROR");
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
