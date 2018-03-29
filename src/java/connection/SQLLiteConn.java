/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Dusan
 */
public class SQLLiteConn 
{
    
    public static Connection getConn()
    {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // db parameters
            String url = "jdbc:mysql://localhost:3306/projekat";
            // create a connection to the database
            conn = DriverManager.getConnection(url,"root","root");
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
    
}
