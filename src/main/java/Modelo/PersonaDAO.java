/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.List;
import java.util.ArrayList;
import Modelo.Persona;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author esnip
 */
public class PersonaDAO {
    Conexion conect=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar(){
        List<Persona> datos = new ArrayList<>();
        String query="select * from persona";
        try {
            con=conect.getConnection();
            ps=con.prepareStatement(query);
            rs=ps.executeQuery();
            while(rs.next()){
                Persona p=new Persona();
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTel(rs.getInt(4));
                datos.add(p);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return datos;
    }
    public int agregarPersona(Persona p){
        String query="insert into persona (Nombre,Correo,Telefono) values(?,?,?)";
        try{
            con=conect.getConnection();
            ps=con.prepareStatement(query);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCorreo());
            ps.setInt(3, p.getTel());
            ps.executeUpdate();
        }catch(SQLException s){
            System.out.println(s);
        }
        return 1;
    }
    public int editarPersona(Persona p){
        int r=0;
        String query="UPDATE persona SET Nombre=?, Correo=?, Telefono=? where id=?";
        try {
            con=conect.getConnection();
            ps=con.prepareStatement(query);
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCorreo());
            ps.setInt(3, p.getTel());
            ps.setInt(4, p.getId());
           r = ps.executeUpdate();
           if(r==1){
               return 1;
           }else{
            return 0;
        }
        } catch (Exception s) {
        }
        return 1;
    }
    public void eliminarPersona (int id){
       
        String query="DELETE from persona where id="+id;
        try {
            con=conect.getConnection();
            ps=con.prepareStatement(query);
            ps.executeUpdate();
            System.out.println("persona eliminadina");
            
        } catch (Exception e) {
            System.out.println(e+" error en eliminar persona");
        }
     
    }
    
}
