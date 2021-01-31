package com.lw.springresttutorial;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(Long id) {
        super(String.format("Could not find employee with id %s", id));
    }
}
