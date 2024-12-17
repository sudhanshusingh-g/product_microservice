package org.example.productservices.exceptions;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String message){
        super(message);
    }
}
