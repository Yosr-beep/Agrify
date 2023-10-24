/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionMateriel.utils;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author HP
 */
public class MyConnection {
    String url = "jdbc:mysql://localhost:3306/gestionmateriel";
    String user = "root";
    String pwd;
    private Connection conn;
    private static MyConnection instance;
    

    private MyConnection() {
        try {
            conn= (Connection) DriverManager.getConnection(url, user ,pwd);
            System.out.println("connection etablie!");
        } catch (SQLException ex) {
            System.out.println("probleme de connection!");
        }
           
    }
    
    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }
        return instance;
    }
    
    public Connection getconn(){
        return MyConnection.getInstance().conn;
    }
}
