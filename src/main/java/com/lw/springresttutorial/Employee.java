package com.lw.springresttutorial;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/*
 * JPA Entities like Employee are used to setup the boilerplate CRUD transactions necessary for
 * communicating with a backend DB
 * Some support pagination and sorting (nice to have for scalability)
 *
 */

// @Entity is used to signal "Employee" is a class used to represent a JPA data store object
@Entity
class Employee {

    // @Id indicates that "id" is the primary key for Employee
    // @GeneratedValue provides for the pattern of generating primary keys for Employees
    private @Id @GeneratedValue Long id;
    private String name;
    private String role;

    // required by JPA
    public Employee() {}

    // used when we don't have an id
    Employee(String name, String role) {

        this.name = name;
        this.role = role;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Employee))
            return false;
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
                && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.role);
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + '}';
    }
}