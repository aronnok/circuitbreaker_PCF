package com.example.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//@EnableDiscoveryClient
@SpringBootApplication
public class CoffeeServiceApplication {
    @Bean
    CommandLineRunner createDemoData(CoffeeRepository repo) {
        return strings -> {
            Stream.of("Jet Black, Smooth dark coffee with hints of jet fuel",
                    "El Café Negro, Un café tostado oscuro con sabor lleno",
                    "Black Gold, Dark earthy roast with overtones of crude oil",
                    "Death Star, Hits you like a weapon of the empire",
                    "Black Alert, Prepare to jump!")
                    .map(string -> string.split(", ", 2))
                    .map(array -> new Coffee(array[0], array[1]))
                    .forEach(repo::save);

            repo.findAll().forEach(System.out::println);
        };
    }

    public static void main(String[] args) {

        SpringApplication.run(CoffeeServiceApplication.class, args);
    }
}
