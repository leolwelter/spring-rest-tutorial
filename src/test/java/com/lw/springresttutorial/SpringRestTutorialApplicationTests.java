package com.lw.springresttutorial;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureTestDatabase
@AutoConfigureMockMvc
class SpringRestTutorialApplicationTests {
    @Autowired
    EmployeeController employeeController;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MockMvc mockMvc;

    @Test
    void contextLoads() {
    }

    @Test
    void preloadOccurs() {
        assert(employeeRepository.findAll().size() == 2);
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/employees")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
