package com.example.server;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/")
public class CoffeeController {
    private final RestOperations restOperations;

    @Value("${defaultcoffee:'Patients Joe'}")
    private String defaultCoffee;

    public CoffeeController(RestOperations restOperations) {
        this.restOperations = restOperations;
    }

    @GetMapping
    String hi() {
        return "Greetings from your friendly neighborhood edge service!";
    }

    @HystrixCommand(fallbackMethod = "getDefaultCoffee")
    @GetMapping("/surpriseme")
    Coffee getRandomCoffee() {

        final String uri = "http://localhost:8080/springrestexample/employees.xml";

        RestTemplate restTemplate = new RestTemplate();

        return restTemplate.getForObject("https://coffee-service-imtiaz-lab.cfapps.io/coffees/random", Coffee.class);
    }

    Coffee getDefaultCoffee() {
        return new Coffee(defaultCoffee, "House choice as described as Hystrix");
    }

}
