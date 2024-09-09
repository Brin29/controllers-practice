package com.sprin.controllers.Exceptions;

// RunTimeException sirve para que en caso de error el programa se cierre sin mostrar error
public class EmployeeNotFoundException extends  RuntimeException{

    public EmployeeNotFoundException(Long id){
        super("Could not find employee " + id);
    }
}
