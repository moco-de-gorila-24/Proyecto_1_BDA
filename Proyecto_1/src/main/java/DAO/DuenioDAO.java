/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.ConexionDB;
import Conexion.IConexion;
import Dominio.Entidades.Duenio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DuenioDAO implements IDuenioDAO {

    private IConexion conexion = new ConexionDB("root", "ITSON");

    @Override
    public boolean insertar(Duenio duenio) {

        String sql = "INSERT INTO dueno (nombre, apellidoP, apellidoM, direccion, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection con = this.conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            cmd.setString(1, duenio.getNombre());
            cmd.setString(2, duenio.getApellidoP());
            cmd.setString(3, duenio.getApellidoM());
            cmd.setString(4, duenio.getDireccion());
            cmd.setString(5, duenio.getEmail());

            int res = cmd.executeUpdate();

            if (res == 0) {
                return false;
            }

            ResultSet claves = cmd.getGeneratedKeys();

            if (claves.next()) {

                int idDueno = claves.getInt(1);

                String sqlTelefono = "INSERT INTO telefono_dueno (telefono, id_dueno) VALUES (?, ?)";

                try (PreparedStatement cmdTelefono = con.prepareStatement(sqlTelefono)) {

                    if (duenio.getTelefonos() != null) {

                        for (String telefono : duenio.getTelefonos()) {
                            cmdTelefono.setString(1, telefono);
                            cmdTelefono.setInt(2, idDueno);
                            cmdTelefono.executeUpdate();
                        }
                    }
                }

                return true;
            }

        } catch (SQLException ex) {
            System.out.println("Error al insertar dueño: " + ex.getMessage());
        }

        return false;
    }

    @Override
    public boolean actualizar(Duenio duenio) {

        String sql = "UPDATE dueno SET nombre=?, apellidoP=?, apellidoM=?, direccion=?, email=? WHERE id_dueno=?";

        try (Connection con = this.conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql)) {

            cmd.setString(1, duenio.getNombre());
            cmd.setString(2, duenio.getApellidoP());
            cmd.setString(3, duenio.getApellidoM());
            cmd.setString(4, duenio.getDireccion());
            cmd.setString(5, duenio.getEmail());
            cmd.setInt(6, duenio.getIdDueno());

            int res = cmd.executeUpdate();

            if (res == 0) {
                return false;
            }

            // Eliminamos los teléfonos anteriores
            String sqlEliminarTelefonos = "DELETE FROM telefono_dueno WHERE id_dueno=?";

            try (PreparedStatement cmdTelefono = con.prepareStatement(sqlEliminarTelefonos)) {
                cmdTelefono.setInt(1, duenio.getIdDueno());
                cmdTelefono.executeUpdate();
            }

            // Insertamos los teléfonos actuales
            String sqlInsertarTelefono = "INSERT INTO telefono_dueno (telefono, id_dueno) VALUES (?, ?)";

            try (PreparedStatement cmdTelefono = con.prepareStatement(sqlInsertarTelefono)) {

                if (duenio.getTelefonos() != null) {

                    for (String telefono : duenio.getTelefonos()) {
                        cmdTelefono.setString(1, telefono);
                        cmdTelefono.setInt(2, duenio.getIdDueno());
                        cmdTelefono.executeUpdate();
                    }
                }
            }

            return true;

        } catch (SQLException ex) {
            System.out.println("Error al actualizar dueño: " + ex.getMessage());
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {

        String sqlTelefono = "DELETE FROM telefono_dueno WHERE id_dueno=?";

        String sqlDueno = "DELETE FROM dueno WHERE id_dueno=?";

        try (Connection con = this.conexion.crearConexionBD()) {

            //Eliminamos sus teléfonos
            try (PreparedStatement cmdTelefono = con.prepareStatement(sqlTelefono)) {
                cmdTelefono.setInt(1, id);
                cmdTelefono.executeUpdate();
            }

            //Eliminamos al dueño
            try (PreparedStatement cmd = con.prepareStatement(sqlDueno)) {
                cmd.setInt(1, id);
                int res = cmd.executeUpdate();

                return res > 0;
            }

        } catch (SQLException ex) {
            System.out.println("Error al eliminar dueño: " + ex.getMessage());
        }

        return false;
    }

    @Override
    public Duenio consultar(int id) {

        String sql = "SELECT id_dueno, nombre, apellidoP, apellidoM, direccion, email FROM dueno WHERE id_dueno=?";

        try (Connection con = this.conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql)) {

            cmd.setInt(1, id);

            ResultSet rs = cmd.executeQuery();

            if (rs.next()) {

                Duenio duenio = new Duenio();

                duenio.setIdDueno(rs.getInt("id_dueno"));
                duenio.setNombre(rs.getString("nombre"));
                duenio.setApellidoP(rs.getString("apellidoP"));
                duenio.setApellidoM(rs.getString("apellidoM"));
                duenio.setDireccion(rs.getString("direccion"));
                duenio.setEmail(rs.getString("email"));

                List<String> telefonos = new ArrayList<>();

                String sqlTelefonos = "SELECT telefono FROM telefono_dueno WHERE id_dueno=?";

                try (PreparedStatement cmdTelefonos = con.prepareStatement(sqlTelefonos)) {

                    cmdTelefonos.setInt(1, id);
                    ResultSet rsTelefonos = cmdTelefonos.executeQuery();

                    while (rsTelefonos.next()) {
                        telefonos.add(rsTelefonos.getString("telefono"));
                    }
                }

                duenio.setTelefonos(telefonos);

                return duenio;
            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar dueño: " + ex.getMessage());
        }

        return null;
    }

    @Override
    public List<Duenio> consultarTodos() {

        List<Duenio> lista = new ArrayList<>();

        String sql = "SELECT id_dueno, nombre, apellidoP, apellidoM, direccion, email FROM dueno";

        try (Connection con = this.conexion.crearConexionBD();
            PreparedStatement cmd = con.prepareStatement(sql)) {

            ResultSet rs = cmd.executeQuery();

            while (rs.next()) {

                Duenio duenio = new Duenio();

                duenio.setIdDueno(rs.getInt("id_dueno"));
                duenio.setNombre(rs.getString("nombre"));
                duenio.setApellidoP(rs.getString("apellidoP"));
                duenio.setApellidoM(rs.getString("apellidoM"));
                duenio.setDireccion(rs.getString("direccion"));
                duenio.setEmail(rs.getString("email"));

                List<String> telefonos = new ArrayList<>();

                String sqlTelefonos = "SELECT telefono FROM telefono_dueno WHERE id_dueno=?";

                try (PreparedStatement cmdTelefonos = con.prepareStatement(sqlTelefonos)) {

                    cmdTelefonos.setInt(1, duenio.getIdDueno());
                    ResultSet rsTelefonos = cmdTelefonos.executeQuery();

                    while (rsTelefonos.next()) {
                        telefonos.add(rsTelefonos.getString("telefono"));
                    }
                }

                duenio.setTelefonos(telefonos);

                lista.add(duenio);
            }

        } catch (SQLException ex) {
            System.out.println("Error al consultar dueños: " + ex.getMessage());
        }

        return lista;
    }
}