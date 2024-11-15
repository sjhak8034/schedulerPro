package com.example.schedulerpro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SchedulerProApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerProApplication.class, args);
    }

}
