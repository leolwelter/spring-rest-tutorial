package com.lw.springresttutorial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootApplication pulls in three things:
 *  component scanning
 *  auto-configuration
 *  property support
 */
@SpringBootApplication
public class SpringRestTutorialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestTutorialApplication.class, args);
    }

}
