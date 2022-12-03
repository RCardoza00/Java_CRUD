/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author esnip
 */
public class Conexion {
    Connection con;
    public Connection getConnection(){
        String url="jdbc:mysql://localhost:3306/crudBD";
        String user="root";
        String pass="";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
        }catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
}
