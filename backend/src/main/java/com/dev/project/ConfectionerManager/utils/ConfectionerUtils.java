package com.dev.project.ConfectionerManager.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ConfectionerUtils
{
    private ConfectionerUtils() {}
    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus)
    {
        return new ResponseEntity<String>("{\"message\":\""+responseMessage+"\"}", httpStatus);
    }
}