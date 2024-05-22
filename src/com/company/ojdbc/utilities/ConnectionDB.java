package com.company.ojdbc.utilities;

import com.company.dao.DAOEmployee;
import com.company.dao.DAOEmployeeFactory;
/*import oracle.jdbc.OracleConnection;
import oracle.jdbc.pool.OracleDataSource;*/

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDB {
    private static Connection instance;

    private ConnectionDB(){};

    public static Connection getInstance() throws SQLException {
        if(instance == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                System.setProperty("oracle.net.tns_admin", "src\\Wallet_Employeedb");

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
           /* OracleDataSource ods = new OracleDataSource();
            ods.setURL(OConnection.url);
            ods.setUser(OConnection.usr);
            ods.setPassword(OConnection.pwd);
            //Disable FAN which is not used, so we don't need to wait for timeout
            Properties prop = ods.getConnectionProperties();
            prop.put("oracle.jdbc.fanEnabled","false");
            ods.setConnectionProperties(prop);
            instance = (OracleConnection) ods.getConnection();

            try {
                setTypeMap((OracleConnection) instance);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
*/

        }
            return instance;
    }

/*    private static void setTypeMap(OracleConnection connection) throws ClassNotFoundException, SQLException{
        java.util.Map map = connection.getTypeMap();
        if (!map.containsKey("EMPLOYEE_TYPII")){
            map.put("EMPLOYEE_TYPII", OEmployee.class);
            connection.setTypeMap(map);
        }
    }*/

    public static void closeConnection() throws SQLException {
        if(instance != null){
            instance.close();
        }
    }
}
