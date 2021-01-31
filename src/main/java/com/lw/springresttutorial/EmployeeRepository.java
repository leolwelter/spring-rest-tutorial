package com.lw.springresttutorial;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Making this interface provides for all the CRUD transactions with our DB
 * JpaRepository<T, ID> takes the T(type of Entity in the repository) and <ID>(type of its Id)
 * This kind of transparent configuration allows us to focus on the DOMAIN logic, not the DB logic
 */
interface EmployeeRepository extends JpaRepository<Employee, Long>{}
