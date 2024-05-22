package com.company.dao;

import com.company.jdbc.utilities.ConnectionDB;
import com.company.model.Employee;

import java.sql.*;
import java.util.ArrayList;

public class DAOMySQLEmployee implements DAOEmployee  {
    Connection con = null;

    public DAOMySQLEmployee() {
        try {
            con = ConnectionDB.getInstance();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ArrayList<Employee> getALLEmployees() throws SQLException {
        try (Statement stmt = con.createStatement()) {
            ArrayList<Employee> emps = new ArrayList<>();
            String query = "SELECT * FROM employee";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int empID = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                Date birthDate = rs.getDate("birthdate");
                float salary = rs.getFloat("salary");
                emps.add(new Employee(empID, firstName, lastName, birthDate, salary));
            }
            return emps;
        }
    }

    @Override
    public Employee getSpecificEmployees(int employeeID) throws SQLException {
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM employee where id = ?")) {
            stmt.setInt(1,employeeID);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                int empID = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                Date birthDate = rs.getDate("birthdate");
                float salary = rs.getFloat("salary");
                return new Employee(empID, firstName, lastName, birthDate, salary);
            }
            return null;
        } catch (SQLException e) {
            throw new SQLException("Failed to retrive a employee record");
        }
    }

    public void deleteEmployee(int identificador) throws Exception {
        try (PreparedStatement stmt = con.prepareStatement("DELETE FROM employee where id = ?")) {
            stmt.setInt(1,identificador);
            stmt.execute();
            } catch (SQLException e) {
            throw new SQLException("Failed to delete a employee record");
        }
    }

    public void addEmployee(Employee nouEmployee) throws SQLException {
        String queryInsert = "INSERT INTO employee(firstname,lastname,birthdate,salary) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(queryInsert)){
            stmt.setString(1,nouEmployee.getFirstName());
            stmt.setString(2,nouEmployee.getLastName());
            stmt.setDate(3, new Date(nouEmployee.getBirthDate().getTime()));
            stmt.setDouble(4,nouEmployee.getSalary());
            stmt.execute();
        }   catch (SQLException e) {
            throw new SQLException("Failed to add employee record");
        }
    }

    @Override
    public void updateEmployee(Employee employeeActualitzar) throws SQLException {
        String queryInsert = "UPDATE employee SET firstname = ?, lastname= ?, salary = ? WHERE id = ?";
        try (PreparedStatement stmt = con.prepareStatement(queryInsert)){
            stmt.setString(1,employeeActualitzar.getFirstName());
            stmt.setString(2,employeeActualitzar.getLastName());
            stmt.setDouble(3,employeeActualitzar.getSalary());
            stmt.setInt(4,employeeActualitzar.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new SQLException("Failed to update a employee record");
        }
    }

    @Override
    public void closeConnection() throws SQLException {
        con.close();
    }

}
