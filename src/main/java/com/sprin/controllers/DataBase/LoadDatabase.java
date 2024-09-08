package com.sprin.controllers.DataBase;

import com.sprin.controllers.Entity.Employee;
import com.sprin.controllers.Entity.Order;
import com.sprin.controllers.Repository.EmployeeRepository;
import com.sprin.controllers.Repository.OrderRepository;
import com.sprin.controllers.StatusEnum.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Para indicar que una clase tiene beans
@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    private final EmployeeRepository employeeRepository;

    public LoadDatabase(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // Encapsular y proporcionar servicios o funcionalidades a otros componentes
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository repository, OrderRepository orderRepository) {

        return args -> {
            log.info("Preloading "
                    + repository.save(new Employee("Bilbo", "Baggins",
                    "burglar")));
            log.info("Preloadin "
                    + repository.save(new Employee("Frodo", "Baggins",
                    "thief")));

            employeeRepository.findAll().forEach(employee -> log.info("Preloaded " + employee));

            orderRepository.save(new Order("MacBook Pro", Status.COMPLETED));
            orderRepository.save(new Order("iPhone", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order ->
            {
                log.info("Preloaded " + order);
            });

        };
    }
}
