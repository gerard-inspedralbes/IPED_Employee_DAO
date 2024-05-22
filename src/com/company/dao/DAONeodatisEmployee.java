package com.company.dao;

import com.company.model.Employee;
import com.company.neodatis.utilities.ConnectionDB;
import org.neodatis.odb.ODB;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

import java.sql.SQLException;
import java.util.ArrayList;

public class DAONeodatisEmployee implements DAOEmployee {
    ODB odb = null;

    public DAONeodatisEmployee() {
        this.odb = ConnectionDB.getInstance();
    }

    @Override
    public ArrayList<Employee> getALLEmployees() throws SQLException {
        Objects<Employee> employees = odb.getObjects(Employee.class);
        return (ArrayList<Employee>) employees;
    }

    @Override
    public Employee getSpecificEmployees(int employeeID) throws SQLException {
        ICriterion criterio = Where.equal("id", employeeID);
        IQuery query = new CriteriaQuery(Employee.class,criterio);
        Objects<Employee> employees = odb.getObjects(query);
        if(employees.hasNext()){
            return employees.getFirst();
        }
        return null;
    }

    @Override
    public void deleteEmployee(int idEmployee) throws SQLException, Exception {

    }

    @Override
    public void addEmployee(Employee employee) throws SQLException {
        odb.store(employee);
    }

    @Override
    public void updateEmployee(Employee employee) throws SQLException {
        ICriterion criterio = Where.equal("id", employee.getId());
        IQuery query = new CriteriaQuery(Employee.class,criterio);
        Objects<Employee> employees = odb.getObjects(query);
        if(employees.hasNext()){
            Employee employeeUpdate = (Employee) employees.getFirst();
            employeeUpdate.setSalary(employee.getSalary());
            employeeUpdate.setLastName(employee.getLastName());
            employeeUpdate.setFirstName(employee.getFirstName());
            odb.store(employeeUpdate);
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        ConnectionDB.closeConnection();
    }
}
