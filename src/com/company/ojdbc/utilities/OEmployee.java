package com.company.ojdbc.utilities;

import java.sql.*;

public class OEmployee implements SQLData {

    private static final String SQL_TYPE_NAME = "EMPLOYEE_TYPII";

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    private String first_name;
    private String last_name;
    private float salary;

    public OEmployee(){
    }

    public OEmployee(String first_name, String last_name, float salary) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.salary = salary;
    }

    @Override
    public String getSQLTypeName() throws SQLException {
        return SQL_TYPE_NAME;
    }

    @Override
    public void readSQL(SQLInput stream, String typeName) throws SQLException {
        this.first_name = stream.readString();
        this.last_name = stream.readString();
        this.salary = stream.readFloat();
    }

    @Override
    public void writeSQL(SQLOutput stream) throws SQLException {
        stream.writeString(this.first_name);
        stream.writeString(this.last_name);
        stream.writeFloat(this.salary);
    }
}
