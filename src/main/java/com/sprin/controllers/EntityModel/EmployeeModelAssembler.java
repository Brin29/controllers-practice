package com.sprin.controllers.EntityModel;

import com.sprin.controllers.Controller.EmployeeController;
import com.sprin.controllers.Entity.Employee;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {

    @Override
    public EntityModel<Employee> toModel(Employee employee) {
        // EntityModel = convierte employee en un EnityModel que contiene los enlaces
        return EntityModel.of(employee,
                // Un enlace a si mismo
                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                // Un enlace a empleados
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }
}
