/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ACER
 */
public class ConexionDB implements IConexion{
    public String user = "root";
    public String pass = "ITSON";
    public String cadenaConexion;

    public ConexionDB(String user, String pass) {
        this.user = user;
        this.pass = pass;
        this.cadenaConexion = "jdbc:mysql://localhost:3306/veterinaria";
    }

    public ConexionDB() {
        this.user = "root";
        this.pass = "ITSON";
        this.cadenaConexion = "jdbc:mysql://localhost:3306/veterinaria  ";
    }

    @Override
    public Connection crearConexionBD() {
        try {
            return DriverManager.getConnection(cadenaConexion, user, pass);
        } catch (SQLException ex) {
            System.out.println("Error al conectar con la base de datos: " + ex.getMessage());
        }

        return null;
    }
}
