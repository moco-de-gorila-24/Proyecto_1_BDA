/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionDB;
import Conexion.IConexion;
import Entidades.Duenio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ACER
 */
public class DuenioDAO implements IDuenioDAO{
     private IConexion conexion = new ConexionDB("root", "ITSON");

    @Override
    public boolean insertar(Duenio duenio) {

        String sql = "INSERT INTO duenio (nombre, apellidoP, apellidoM, direccion, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)) {

            cmd.setString(1, duenio.getNombre());
            cmd.setString(2, duenio.getApellidoP());
            cmd.setString(3, duenio.getApellidoM());
            cmd.setString(4, duenio.getDireccion());
            cmd.setString(5, duenio.getEmail());

            int res = cmd.executeUpdate();

            return res > 0;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    @Override
    public boolean actualizar(Duenio duenio) {

        String sql = "UPDATE dueno SET nombre=?, apellidoP=?, apellidoM=?, direccion=?, email=? WHERE id_duenio=?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)) {

            cmd.setString(1, duenio.getNombre());
            cmd.setString(2, duenio.getApellidoP());
            cmd.setString(3, duenio.getApellidoM());
            cmd.setString(4, duenio.getDireccion());
            cmd.setString(5, duenio.getEmail());
            cmd.setInt(6, duenio.getIdDueno());

            int res = cmd.executeUpdate();

            return res > 0;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {

        String sql = "DELETE FROM dueno WHERE id_duenio=?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)) {

            cmd.setInt(1, id);

            int res = cmd.executeUpdate();

            return res > 0;

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return false;
    }

    @Override
    public Duenio consultar(int id) {

        String sql = "SELECT id_dueno, nombre, apellidoP, apellidoM, direccion, email FROM dueno WHERE id_duenio=?";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)) {

            cmd.setInt(1, id);

            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {

                Duenio duenio = new Duenio();

                duenio.setIdDueno(rs.getInt("id_duenio"));
                duenio.setNombre(rs.getString("nombre"));
                duenio.setApellidoP(rs.getString("apellidoP"));
                duenio.setApellidoM(rs.getString("apellidoM"));
                duenio.setDireccion(rs.getString("direccion"));
                duenio.setEmail(rs.getString("email"));

                return duenio;
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return null;
    }

    @Override
    public List<Duenio> consultarTodos() {

        List<Duenio> lista = new ArrayList<>();

        String sql = "SELECT id_dueno, nombre, apellidoP, apellidoM, direccion, email FROM duenio";

        try (Connection con = this.conexion.crearConexionBD();
             PreparedStatement cmd = con.prepareStatement(sql)) {

            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {

                Duenio duenio = new Duenio();

                duenio.setIdDueno(rs.getInt("id_duenio"));
                duenio.setNombre(rs.getString("nombre"));
                duenio.setApellidoP(rs.getString("apellidoP"));
                duenio.setApellidoM(rs.getString("apellidoM"));
                duenio.setDireccion(rs.getString("direccion"));
                duenio.setEmail(rs.getString("email"));

                lista.add(duenio);
            }

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return lista;
    }
}
