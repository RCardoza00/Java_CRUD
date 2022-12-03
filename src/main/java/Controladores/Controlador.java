/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Modelo.Persona;
import Modelo.PersonaDAO;
import Vistas.Vista;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author esnip
 */
public class Controlador implements ActionListener {
    PersonaDAO dao=new PersonaDAO();
    Persona p=new Persona();
    Vista vista=new Vista();
    DefaultTableModel modelo=new DefaultTableModel();
    public Controlador(Vista v){
        this.vista=v;
        this.vista.btnListar.addActionListener(this);
        this.vista.btnGuardar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnOk.addActionListener(this);
        listar(vista.tabla);
        this.vista.btnEliminar.addActionListener(this);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("probando123");
        if(e.getSource()==vista.btnListar){
            limpiarTabla(vista.tabla);
            listar(vista.tabla);
        }
        if(e.getSource()==vista.btnGuardar){
            agregarPersona();
            
            System.out.println("agregando personaaaaa");
        }
        if(e.getSource()==vista.btnEditar){
            int fila=vista.tabla.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(vista, "Selecciona una fila para editar");
            }else{
                int id=Integer.parseInt(vista.tabla.getValueAt(fila, 0).toString());
                String nombre= vista.tabla.getValueAt(fila, 1).toString();
                String correo = vista.tabla.getValueAt(fila, 2).toString();
                int telefono = Integer.parseInt(vista.tabla.getValueAt(fila,3).toString());
                vista.txtID.setText(String.valueOf(id));
                vista.txtNombre.setText(nombre);
                vista.txtCorreo.setText(correo);
                vista.txtTelefono.setText(String.valueOf(telefono));
                System.out.println("fila seleccionada");
            }
        }
        //si le dan al boton ok se ejecuta el metodo de update
        if(e.getSource()==vista.btnOk){
             System.out.println("boton ok");
            Actualizar();
           
        }
        if(e.getSource()==vista.btnEliminar){
            int fila=vista.tabla.getSelectedRow();
            if(fila==-1){
                JOptionPane.showMessageDialog(vista, "Debes seleccionar una fila");
            }else{
            int id=Integer.parseInt(vista.tabla.getValueAt(fila, 0).toString());

                dao.eliminarPersona(id);
                JOptionPane.showMessageDialog(vista, "Usuario eliminado");
            }
            limpiarTabla(vista.tabla);
            listar(vista.tabla);
        }
        
    }
    public void listar(JTable table){
        modelo=(DefaultTableModel)table.getModel();
        List<Persona>lista=dao.listar();
        Object[] object=new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            object[0]=lista.get(i).getId();
            object[1]=lista.get(i).getNombre();
            object[2]=lista.get(i).getCorreo();
            object[3]=lista.get(i).getTel();
            modelo.addRow(object);
        }
        vista.tabla.setModel(modelo);
    }
    public void limpiarTabla(JTable table){
        modelo=(DefaultTableModel)table.getModel();
        modelo.setRowCount(0);
    }
    
    public void agregarPersona(){
        int tel;
        String nombre,correo;
        nombre=vista.txtNombre.getText();
        correo=vista.txtCorreo.getText();
        tel=Integer.parseInt(vista.txtTelefono.getText());
        p.setNombre(nombre);
        p.setCorreo(correo);
        p.setTel(tel);
        int r=dao.agregarPersona(p);
        if(r==1){
            JOptionPane.showMessageDialog(vista, "usuario agregado con exito");
            vista.txtNombre.setText("");
            vista.txtCorreo.setText("");
            vista.txtTelefono.setText("");
        }else{
            JOptionPane.showMessageDialog(vista, "Error agregando usuario");
        }
        
    }
    public void limpiarCampos(){
         vista.txtNombre.setText("");
            vista.txtCorreo.setText("");
            vista.txtTelefono.setText("");
    }
    public void Actualizar(){
        int id = Integer.parseInt(vista.txtID.getText().toString());
        int tel=Integer.parseInt(vista.txtTelefono.getText());;
        String nombre,correo;
        nombre=vista.txtNombre.getText();
        correo=vista.txtCorreo.getText();
        p.setId(id);
        p.setNombre(nombre);
        p.setCorreo(correo);
        p.setTel(tel);
        int response = dao.editarPersona(p);
        if(response==1){
            JOptionPane.showMessageDialog(vista, "Persona editada con exito");
        }else{
            JOptionPane.showMessageDialog(vista, "error editando persona");
        }
    }
    public void eliminarPersona(){
        int id=Integer.parseInt(vista.txtID.getText().toString());
    }
    
}
