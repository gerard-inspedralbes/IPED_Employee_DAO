package com.company.dao;

import com.company.model.Employee;
import com.company.ojdbc.utilities.ConnectionDB;
import com.company.ojdbc.utilities.OEmployee;

import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class DAOOracleEmployee implements DAOEmployee {
    Connection con = null;

    public DAOOracleEmployee() {
        try {
            con = ConnectionDB.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Override
    public ArrayList<Employee> getALLEmployees() throws SQLException {
        ArrayList<Employee> emps = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM employee")) {
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    int employeeID = rset.getInt(1);
                    OEmployee oe = (OEmployee)rset.getObject(2);
                    emps.add(new Employee(employeeID,oe.getFirst_name(),oe.getLast_name(),null,oe.getSalary()));
                }
            }
        }
        return emps;
    }

    @Override
    public Employee getSpecificEmployees(int employeeID) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement("SELECT Employee FROM employee where ID_EMPLOYEE = ?")) {
            stmt.setInt(1,employeeID);
            try (ResultSet rset = stmt.executeQuery()) {
                while (rset.next()) {
                    OEmployee oe = (OEmployee)rset.getObject(1);
                    return new Employee(employeeID,oe.getFirst_name(),oe.getLast_name(),null,oe.getSalary());
                }
            }
        }
        return null;
    }

    @Override
    public void deleteEmployee(int idEmployee) throws Exception {
        try (PreparedStatement stmt = con.prepareStatement("DELETE FROM employee where ID_EMPLOYEE = ?")) {
            stmt.setInt(1,idEmployee);
            stmt.execute();
        } catch (SQLException e) {
            throw new Exception("Failed to delete a employee record");
        }
    }

    @Override
    public void addEmployee(Employee e) throws SQLException {
        OEmployee oEmployee = new OEmployee(e.getFirstName(),e.getLastName(),e.getSalary());
        try (PreparedStatement pstmt = con.prepareStatement("insert into employee values(DEFAULT,?)")) {
            pstmt.setObject(1, oEmployee);
            pstmt.executeUpdate();
        }
    }



    @Override
    public void updateEmployee(Employee employee) throws SQLException {

    }

    @Override
    public void closeConnection() throws SQLException {
        con.close();
    }


}
