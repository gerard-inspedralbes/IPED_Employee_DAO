package com.company.dao;

import com.company.model.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOEmployee {

    ArrayList<Employee> getALLEmployees() throws Exception;
    Employee getSpecificEmployees(int employeeID) throws Exception;
    void deleteEmployee(int idEmployee) throws Exception;
    void addEmployee(Employee employee) throws Exception;
    void updateEmployee(Employee employee) throws Exception;
    void closeConnection() throws Exception ;
}
