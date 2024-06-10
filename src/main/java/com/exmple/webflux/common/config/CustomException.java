package com.exmple.webflux.common.config;

public class CustomException extends RuntimeException{
    private static final long serialVersionUID = -5970845585469454688L;


    public CustomException(String type) {
        System.out.println("My-Error: "+type + " is not found");
    }

}
