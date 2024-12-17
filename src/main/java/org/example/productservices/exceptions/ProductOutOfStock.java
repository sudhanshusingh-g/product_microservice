package org.example.productservices.exceptions;

public class ProductOutOfStock extends Exception {
    public ProductOutOfStock( String message){
        super(message);
    }
}
