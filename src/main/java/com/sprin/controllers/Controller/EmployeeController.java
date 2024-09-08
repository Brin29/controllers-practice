package com.sprin.controllers.Controller;

import com.sprin.controllers.Entity.Employee;
import com.sprin.controllers.Exceptions.EmployeeNotFoundException;
import com.sprin.controllers.Repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    // Inyeccion de la dependencia
    private final EmployeeRepository repository;

    public EmployeeController(EmployeeRepository repository){
        this.repository = repository;
    }

    @GetMapping("/employees")
    public List<Employee> all() {
        return repository.findAll();
    };

    @PostMapping("/employees")
    public Employee newEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    };

    @GetMapping("/employees/{id}")
    public Employee one(@PathVariable Long id){
        return repository.findById(id).orElseThrow(() -> new EmployeeNotFoundException(id));
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
