package com.lw.springresttutorial;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/*
 * Consider ControllerAdvice to be a kind of interceptor
 * When the associated Exception gets thrown, you can handle it with a custom handler
 */
@ControllerAdvice
@ResponseBody
public class EmployeeNotFoundAdvice {
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notFoundHandler(Exception e) {
        return String.format("Whoops, looks like you're a dummy. Figure this crap out: %s", e.getMessage());
    }
}
