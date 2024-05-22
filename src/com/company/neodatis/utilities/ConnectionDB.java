package com.company.neodatis.utilities;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;

public class ConnectionDB {
    private static ODB instance;

    private ConnectionDB(){};

    public static ODB getInstance(){
        if(instance == null) {
            instance = ODBFactory.open("neodatis.employees");
        }
            return instance;
    }

    public static void closeConnection() {
        if(instance != null){
            instance.close();
        }
    }
}
