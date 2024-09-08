package com.sprin.controllers.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Employee {

    private @Id
    @GeneratedValue Long id;

    private String firstName;

    private String lastName;

    private String role;

    public Employee(){}

    public Employee(String firstName, String lastName ,String role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getId(){
        return id;
    }

    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    public String getFirstName(){
        return this.firstName;
    }

    public String getLastName(){
        return this.lastName;
    }

    public String getRole(){
        return role;
    }

    public void setId(long id){
        this.id = id;
    }

    public void setName(String name){
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public void setRole(String role){
        this.role = role;
    }

    // Sobreescribir un metodo de una clase padre o heredada
    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;

        if (!(o instanceof Employee))
            return false;

        Employee employee = (Employee) o;

        return Objects.equals(this.id, employee.id) &&
            Objects.equals(this.firstName, employee.firstName)
            && Objects.equals(this.lastName, employee.lastName)
            && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.firstName, this.lastName,this.role);
    }

    @Override
    public String toString(){
        return "Employee{"
            + "id=" + this.id + ", firstName='"
            + this.firstName + '\'' + ", lastName='"
            + this.lastName + '\'' + ", role='"
            + this.role + '\'' + '}';
    }
}
