package com.lw.springresttutorial;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/*
 * Means that the data returned by the controller's methods are written
 * STRAIGHT TO THE RESPONSE BODY, not in a template
 * (has @ResponseBody, which does this)
 */
@RestController
class EmployeeController {

    private final EmployeeRepository repository;
    private final EmployeeModelAssembler modelAssembler;

    // Spring handles dependency injection
    EmployeeController(EmployeeRepository repository, EmployeeModelAssembler modelAssembler) {
        this.repository = repository;
        this.modelAssembler = modelAssembler;
    }


    // Aggregate root
    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> all() {
        // first we get a List of Employees
        // then we map each one in the stream of elements to the EmployeeModelAssembler
        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(modelAssembler::toModel).collect(Collectors.toList());
        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return repository.save(newEmployee);
    }

    // Single item
    @GetMapping("/employees/{id}")
    EntityModel<Employee> one(@PathVariable Long id) {
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
        // we use the EmployeeModelAssembler (EntityModelAssembler) to create resource links
        // autowired by Spring, based on the Controller's methods' routes. Neat, huh?
        return modelAssembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
