package com.lw.springresttutorial;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class PreLoadDataBase {
    /*
     * CommandLineRunner is scanned for by Spring and run just after CONTEXT is loaded
     * initDatabase injects the dependency EmployeeRepository and uses it to save
     * two Employee Entities
     */
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repo) {
        return args -> {
            repo.save(new Employee("Thomas Shelby", "Mastermind"));
            repo.save(new Employee("Arthur Shelby", "Ruffian"));
        };
    }
}
