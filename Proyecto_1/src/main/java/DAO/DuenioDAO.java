/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.Conexion;
import Entidades.Duenio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class DuenioDAO {
    public List<Duenio> listar() {

        List<Duenio> lista = new ArrayList<>();

        String sql = "SELECT id_dueno, nombre, apellidoP, apellidoM, direccion, email FROM dueno";

        try (Connection conexion = Conexion.getConexion(); PreparedStatement ps = conexion.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Duenio duenio = new Duenio();

                duenio.setIdDueno(rs.getInt("id_dueno"));
                duenio.setNombre(rs.getString("nombre"));
                duenio.setApellidoP(rs.getString("apellidoP"));
                duenio.setApellidoM(rs.getString("apellidoM"));
                duenio.setDireccion(rs.getString("direccion"));
                duenio.setEmail(rs.getString("email"));

                lista.add(duenio);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar dueños: " + e.getMessage());
        }

        return lista;
    }
}
