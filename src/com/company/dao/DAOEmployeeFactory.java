package com.company.dao;

public class DAOEmployeeFactory {

    public static DAOEmployee createDAOEmployee(String BDType) {
        if(BDType.equalsIgnoreCase("oracle")){
            return new DAOOracleEmployee();
        }else if(BDType.equalsIgnoreCase("mysql")) {
            return new DAOMySQLEmployee();
        }else if(BDType.equalsIgnoreCase("neodatis")) {
            return new DAONeodatisEmployee();
        }
        return null;
    }
}
