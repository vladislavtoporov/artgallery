package ru.kpfu.itis.artgallery.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan("ru.kpfu.itis")
@EnableJpaRepositories(basePackages = "ru.kpfu.itis.artgallery.repositories")
@EntityScan(basePackages = "ru.kpfu.itis.artgallery.models")
public class Application {
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}
