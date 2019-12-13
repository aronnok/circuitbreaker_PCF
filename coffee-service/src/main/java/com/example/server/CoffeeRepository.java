package com.example.server;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    @Query("select c from Coffee c order by rand()")
    Iterable<Coffee> getCoffeesRandomOrder();
}
