package com.sprin.controllers.Controller;

import com.sprin.controllers.Entity.Employee;
import com.sprin.controllers.Exceptions.EmployeeNotFoundException;
import com.sprin.controllers.Repository.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
public class EmployeeController {

    // Inyeccion de la dependencia
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }

    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all() {

        List<EntityModel<Employee>> employees = repository.findAll().stream()
            .map(employee -> EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"))).collect(Collectors.toList());

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).all()).withSelfRel());

    };

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    };

    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable Long id){

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return EntityModel.of(employee,
            linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
            linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }

    @PutMapping("/employees/{id}")
    public Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
        return repository.findById(id).map(employee -> {
            employee.setName(newEmployee.getName());
            employee.setRole(newEmployee.getRole());
            return repository.save(employee);
        })
        .orElseGet(() -> {
            return repository.save(newEmployee);
        });
    }

    @DeleteMapping("/employess/{id}")
    public void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }

}
