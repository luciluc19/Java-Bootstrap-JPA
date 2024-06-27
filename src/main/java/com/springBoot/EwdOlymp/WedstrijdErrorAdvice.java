package com.springBoot.EwdOlymp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import exception.SportNietGevondenException;
import exception.WedstrijdIdNietGevondenException;

@RestControllerAdvice
public class WedstrijdErrorAdvice {

  @ResponseBody
  @ExceptionHandler(SportNietGevondenException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String SportNotFoundHandler(SportNietGevondenException ex) {
    return ex.getMessage();
  }

  @ResponseBody
  @ExceptionHandler(WedstrijdIdNietGevondenException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String WedstrijdIdNotFoundHandler(WedstrijdIdNietGevondenException ex) {
    return ex.getMessage();
  }
    
}
